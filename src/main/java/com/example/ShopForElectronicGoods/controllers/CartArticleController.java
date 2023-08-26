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



}
