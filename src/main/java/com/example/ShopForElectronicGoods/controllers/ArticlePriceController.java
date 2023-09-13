package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.services.ArticlePriceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articlePrice")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticlePrice> addArticlePriceForArticle(@Valid @RequestBody ArticlePrice articlePrice,
                                                                  @PathVariable final Integer articleId){
        ArticlePrice a = articlePriceService.addArticlePrice(articlePrice,articleId);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }


    @PutMapping("/editAP/{articlePriceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticlePrice> editArticlePrice(@Valid @RequestBody ArticlePrice articlePrice,
                                                         @PathVariable final Integer articlePriceId){
        ArticlePrice ap = articlePriceService.editArticlePrice(articlePrice,articlePriceId);
        return ResponseEntity.ok(ap);
    }

    @DeleteMapping("/{articlePriceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteArticlePriceByID(@PathVariable final Integer articlePriceId){
        ArticlePrice ap = articlePriceService.getArticlePriceById(articlePriceId);
        if(ap == null){
            throw new ApiRequestException("Article price by ID " + articlePriceId + " not found", HttpStatus.NOT_FOUND);
        }
        articlePriceService.deleteArticlePriceById(articlePriceId);
        throw new ApiRequestException("Article price by ID " + articlePriceId + " deleted successfully ", HttpStatus.OK);
    }

}
