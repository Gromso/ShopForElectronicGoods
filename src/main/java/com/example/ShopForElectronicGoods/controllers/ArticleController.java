package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleAddDTO;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleEditDTO;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleSearchDTO;
import com.example.ShopForElectronicGoods.services.ArticleServices.ArticleResponseService;
import com.example.ShopForElectronicGoods.services.ArticleServices.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleResponseService articleResponseService;

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer articleId){
        Article article = articleService.findArticleById(articleId);
        if(article != null){
            return ResponseEntity.ok(article);
        }
        throw new ApiRequestException("Article by ID " + articleId + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDTO>> getAllArticle(){
        List<ArticleDTO> allArticle = articleService.getFullArticles();
        return ResponseEntity.ok(allArticle);
    }

    @PostMapping("/searchh")
    public ResponseEntity<List<ArticleDTO>> searchh(@RequestBody ArticleSearchDTO body){
        List<ArticleDTO> listArticleDTO = articleService.getFullArticles();
        List<ArticleDTO> articles =articleResponseService.filterArticles(listArticleDTO ,body);
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/add/articleDto")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> addArticleWithDto(@Valid @RequestBody ArticleAddDTO body){
        Article ad = articleService.addArticleDTO(body);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    @PutMapping("/editFull/{articleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> editFullArticle(@Valid @RequestBody ArticleEditDTO article,
                                                   @PathVariable final Integer articleId){
        Article article1 = articleService.editFullArticle(article, articleId);

        return ResponseEntity.ok(article1);
    }



   // @DeleteMapping("/delete/{articleId}")
   // public void deleteArticleById(@PathVariable Integer articleId){
       // articleService.deleteArticleById(articleId);
   // }




}