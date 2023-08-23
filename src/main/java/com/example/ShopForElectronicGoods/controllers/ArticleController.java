package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.models.Feature;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.ArticleAddDTO;
import com.example.ShopForElectronicGoods.services.ArticleServices.ArticleArticlePriceService;
import com.example.ShopForElectronicGoods.services.ArticleServices.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleArticlePriceService articleArticlePriceService;

    @GetMapping("/{articleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article getArticleById(@PathVariable Integer articleId){
        return articleService.findArticleById(articleId);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticle(){
        List<Article> allArticle = articleService.findAllArticle();
        return ResponseEntity.ok(allArticle);
    }

    @GetMapping("/all/{articleId}/articleFeature")
    public ResponseEntity<List<Feature>> getArticleFeatureBuArticleId(@PathVariable final Integer articleId){
        List<Feature> af = articleService.findArticleFeatureByArticle(articleId);
        return new ResponseEntity<>(af,HttpStatus.FOUND);
    }

 @PostMapping("/add/{categoryId}/article")
 public ResponseEntity<Article> addArticle(@PathVariable Integer categoryId,
                                           @RequestBody Article article) {
    Article articleArticle = articleService.addArticle(article,categoryId);
     return new ResponseEntity<>(articleArticle, HttpStatus.CREATED);
 }

 @PostMapping("/add/articleDto")
 public ResponseEntity<Article> addArticleWithDto(@RequestBody ArticleAddDTO body){
        Article ad = articleService.addArticleDTO(body);

        return new ResponseEntity<>(ad, HttpStatus.CREATED);
 }

    @PutMapping("/edit/{articleId}")
    public Article editArticleById(@RequestBody Article article,@PathVariable Integer articleId){
        return articleService.editArticleOrSave(article,articleId);
    }

    @DeleteMapping("/delete/{articleId}")
    public void deleteArticleById(@PathVariable Integer articleId){
        articleService.deleteArticleById(articleId);
    }

    @GetMapping("/articlePrice/{articleId}")
    public ResponseEntity<List<ArticlePrice>> getPricesForArticle(@PathVariable Integer articleId) {
        Article article = articleService.findArticleById(articleId);
        if (article == null) {
            // Ovdje možete obraditi slučaj kada članak nije pronađen
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ArticlePrice> allArticlePrices = articleArticlePriceService.getPricesForArticlee(article);
        return new ResponseEntity<>(allArticlePrices, HttpStatus.OK);
    }

    @GetMapping("/articlePrice/article/{articleId}")
    public ResponseEntity<List<ArticlePrice>> getArticleWithArticlePrice(@PathVariable final Integer articleId){
        Article article = articleService.findArticleById(articleId);
        if (article == null) {
            // Ovdje možete obraditi slučaj kada članak nije pronađen
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ArticlePrice> allArticlePrices = articleArticlePriceService.getPricesForArticle(article);
        return new ResponseEntity<>(allArticlePrices, HttpStatus.OK);
    }

    @GetMapping("/article/latestPrice/{articleId}")
    public ResponseEntity<Article> findArticleWithLatestPrice(@PathVariable Integer articleId) {
        Article article = articleService.findArticleById(articleId);
        if (article == null) {
            // Ovdje možete obraditi slučaj kada članak nije pronađen
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Article articlee = articleArticlePriceService.findArticleWithLatestPrice(article);
        return new ResponseEntity<>(articlee, HttpStatus.OK);
    }

}