package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.CartArticle;
import com.example.ShopForElectronicGoods.modelsDTO.CartArticleDTO;
import com.example.ShopForElectronicGoods.services.CartArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartArticle")
public class CartArticleController {


    @Autowired
    private CartArticleService cartArticleService;





    @PostMapping("/add/{cartId}/cart/{articleId}/article")
    public ResponseEntity<CartArticleDTO> addCartArticleByCartIdAndArticleId(@RequestBody CartArticleDTO caDTO,
                                                                             @PathVariable final Integer cartId,
                                                                             @PathVariable final Integer articleId){
        CartArticle ca = cartArticleService.addCartArticleByCartIdAndArticleId(CartArticle.from(caDTO),
                cartId,articleId);
        return new ResponseEntity<>(CartArticleDTO.from(ca), HttpStatus.CREATED);
    }

}
