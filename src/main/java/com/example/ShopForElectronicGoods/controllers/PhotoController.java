package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Photo;
import com.example.ShopForElectronicGoods.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotoController {






    @Autowired
    private PhotoService photoService;





    @PostMapping("/article/{articleId}")
    public ResponseEntity<Photo> savePhotoForArticleId(@RequestParam("file") MultipartFile file, @PathVariable Integer articleId) throws IOException {

            Photo photo = photoService.savePhotoForArticle(file, articleId);
            return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }


    @DeleteMapping("/{photoId}")
    public void deletePhoto(@PathVariable final Integer photoId) throws IOException {
        photoService.deletePhotoForArticle(photoId);
    }


}
