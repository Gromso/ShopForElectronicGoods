package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.services.ArticlePriceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articlePrice")
public class ArticlePriceController {



    @Autowired
    private ArticlePriceService articlePriceService;


    @GetMapping("/{articlePriceId}")
    public ResponseEntity<ArticlePrice> getArticlePriceById(@PathVariable final Integer articlePriceId) {
        ArticlePrice articlePrice = articlePriceService.getArticlePriceById(articlePriceId);
        return ResponseEntity.ok(articlePrice);
    }

    @GetMapping("/articlePrices")
    public ResponseEntity<List<ArticlePrice>> getAllArticlePrice() {
        List<ArticlePrice> articles = articlePriceService.getAllArticlePrice();
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/add/{articleId}")
    public ResponseEntity<ArticlePrice> addArticlePriceForArticle(@RequestBody ArticlePrice articlePrice,
                                                                  @PathVariable final Integer articleId){
        ArticlePrice a = articlePriceService.addArticlePrice(articlePrice,articleId);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }


    @PutMapping("/editAP/{articlePriceId}")
    public ResponseEntity<ArticlePrice> editArticlePrice(@RequestBody ArticlePrice articlePrice,
                                                         @PathVariable final Integer articlePriceId){
        ArticlePrice ap = articlePriceService.editArticlePrice(articlePrice,articlePriceId);
        return ResponseEntity.ok(ap);
    }

    @DeleteMapping("/articlePriceId")
    public void deleteArticlePriceByID(@PathVariable final Integer articlePriceId){
        articlePriceService.deleteArticlePriceById(articlePriceId);
    }

}
