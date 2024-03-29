package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Photo;
import com.example.ShopForElectronicGoods.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {


    @Autowired
    private PhotoService photoService;


    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/photos/{articleId}")
    public ResponseEntity<List<Photo>> getPhotosByArticleId(@PathVariable Integer articleId){
        List<Photo> photos = photoService.getPhotosByArticleId(articleId);
        return ResponseEntity.ok(photos);
    }

    @PostMapping("/article/{articleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Photo> savePhotoForArticleId(@RequestParam("file") MultipartFile file, @PathVariable Integer articleId) throws IOException {

            Photo photo = photoService.savePhotoForArticle(file, articleId);
            return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }


    @DeleteMapping("/{photoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePhoto(@PathVariable final Integer photoId) throws IOException {
        photoService.deletePhotoForArticle(photoId);
    }


}
