package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.services.ArticlePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articlePrice")
public class ArticlePriceController {



    @Autowired
    private ArticlePriceService articlePriceService;



    @PostMapping("/add/{articleId}")
    public ResponseEntity<ArticlePrice> addArticlePriceForArticle(@RequestBody ArticlePrice articlePrice,
                                                                  @PathVariable final Integer articleId){
        ArticlePrice a = articlePriceService.addArticlePrice(articlePrice,articleId);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

}
