package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import com.example.ShopForElectronicGoods.services.ArticleService;
import com.example.ShopForElectronicGoods.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{articleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article getArticleById(@PathVariable Integer articleId){
        return articleService.findArticleById(articleId);
    }

    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody Article article){
        try{
            Article saveArticle = articleService.addArticle(article);
            return ResponseEntity.ok(saveArticle);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
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