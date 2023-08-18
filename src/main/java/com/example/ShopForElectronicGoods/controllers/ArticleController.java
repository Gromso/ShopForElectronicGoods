package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import com.example.ShopForElectronicGoods.services.ArticleService;
import com.example.ShopForElectronicGoods.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{articleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article getArticleById(@PathVariable Integer articleId){
        return articleService.findArticleById(articleId);
    }

 /*   @PostMapping("/add/{categoryId}/article")
    public ResponseEntity<Article> addArticle(@PathVariable Integer categoryId,
            @RequestBody Article article){
        try{
            Article saveArticle = articleService.addArticle(article,categoryId);
            return ResponseEntity.ok(saveArticle);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }*/
 @PostMapping("/add/{categoryId}/article")
 public ResponseEntity<Article> addArticle(@PathVariable Integer categoryId,
                                           @RequestBody Article article) {
    Article articleArticle = articleService.addArticle(article,categoryId);
     return new ResponseEntity<>(articleArticle, HttpStatus.CREATED);
 }


    @PutMapping("/edit/{articleId}")
    public Article editArticleById(@RequestBody Article article,@PathVariable Integer articleId){
        return articleService.editArticleOrSave(article,articleId);
    }

    @DeleteMapping("/delete/{articleId}")
    public void deleteArticleById(@PathVariable Integer articleId){
        articleService.deleteArticleById(articleId);
    }
}