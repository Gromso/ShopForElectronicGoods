package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.configuration.AddPhotoConfig;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Photo;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.PhotoRepository;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

@Service
public class PhotoService {


    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${spring.servlet.multipart.max-file-size}")
    private int maxFileSize;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ArticleRepository articleRepository;



    public Photo savePhotoForArticle(MultipartFile file, Integer articleId) throws IOException {
        if (file.isEmpty()) {
            throw new ApiRequestException("File not found");
        }
        String mimeType = new Tika().detect(file.getInputStream());

        // Dopustite samo određene MIME tipove (npr. image/jpeg i image/png)
        if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png")) {
           throw new ApiRequestException("Samo JPEG i PNG slike su dozvoljene.", HttpStatus.NOT_ACCEPTABLE);
        }
       /* if (file.getSize() > 1) {
            throw new ApiRequestException("Only one file is allowed");
        }*/

        long fileSizeInBytes = file.getSize();
        if(fileSizeInBytes > maxFileSize){
            throw new ApiRequestException("Max size for file is 2Md");
        }


        String image_path = AddPhotoConfig.generateFileName(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(uploadDir, image_path);

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ApiRequestException("Article with id " + articleId + " not found"));

        Photo photo = new Photo();
        photo.setArticle(article);
        photo.setImage_path(image_path);

        Files.write(filePath, file.getBytes());

        return photoRepository.save(photo);
    }


}
