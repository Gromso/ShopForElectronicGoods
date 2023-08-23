package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.configuration.AddPhotoConfig;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Photo;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.PhotoRepository;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

@Service
public class PhotoService {


    @Value("${upload.dir}")
    private String uploadDir;


    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ArticleRepository articleRepository;



   public Photo savePhotoForArticle(MultipartFile file, Integer articleId) throws IOException {
       if (file.isEmpty()) {
           throw new ApiRequestException("File is Empty");
       }
       String mimeType = new Tika().detect(file.getInputStream());

       // Dopustite samo određene MIME tipove (npr. image/jpeg i image/png)
       if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png")) {
           throw new ApiRequestException("Samo JPEG i PNG slike su dozvoljene.", HttpStatus.NOT_ACCEPTABLE);
       }
       String image_Name = AddPhotoConfig.generateFileName(Objects.requireNonNull(file.getOriginalFilename()));

       Path originalFilePath = Paths.get(uploadDir+"normalImages/", image_Name);
       Path smallFilePath = Paths.get(uploadDir + "small/", image_Name);
       Path thumbnailFilePath = Paths.get(uploadDir + "thumb/", image_Name);


       try {

           AddPhotoConfig.uploadOriginalFile(originalFilePath, file);
           AddPhotoConfig.uploadSmallFile(originalFilePath,smallFilePath);
           AddPhotoConfig.uploadThumbnailFile(originalFilePath, thumbnailFilePath);


       } catch (IOException e) {
           throw new ApiRequestException("Greška pri spremanju slike", HttpStatus.INTERNAL_SERVER_ERROR);
       }

       Article article = articleRepository.findById(articleId)
               .orElseThrow(() -> new ApiRequestException("Article with id " + articleId + " not found"));

       Photo photo = new Photo();
       photo.setArticle(article);
       photo.setImage_path(image_Name);

       return photoRepository.save(photo);
   }



   public void deletePhotoForArticle( final Integer photoId) throws IOException {

       Photo photoId2 = photoRepository.findById(photoId).orElseThrow(() ->
               new ApiRequestException("Photo by id " + photoId + " not found", HttpStatus.NOT_FOUND));

       try {
           Path originalFilePath = Paths.get("D:\\java_programs\\projekti\\storage\\photos" + "\\normalImages\\" + photoId2.getImage_path());
           Path smallFilePath = Paths.get("D:\\java_programs\\projekti\\storage\\photos" + "\\small\\" + photoId2.getImage_path());
           Path thumbnailFilePath = Paths.get("D:\\java_programs\\projekti\\storage\\photos" + "\\thumb\\" + photoId2.getImage_path());
           Files.delete(originalFilePath);
           Files.delete(smallFilePath);
           Files.delete(thumbnailFilePath);
       }catch (NoSuchFileException e){
           e.getMessage();
           throw new ApiRequestException("the image in the given path was not found", HttpStatus.BAD_REQUEST);
       }
       deletePhoto(photoId);
   }

   public void deletePhoto (final Integer photoId){
        photoRepository.deleteById(photoId);
   }

}
