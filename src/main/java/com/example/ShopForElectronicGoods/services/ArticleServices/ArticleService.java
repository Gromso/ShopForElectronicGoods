package com.example.ShopForElectronicGoods.services.ArticleServices;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.*;
import com.example.ShopForElectronicGoods.models.ENUMS.ArticleStatusEnum;
import com.example.ShopForElectronicGoods.modelsDTO.ArticleDTO.*;
import com.example.ShopForElectronicGoods.repository.*;

import com.example.ShopForElectronicGoods.services.ArticlePriceService;
import com.example.ShopForElectronicGoods.services.PhotoService;
import jakarta.persistence.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleResponseService articleResponseService;

    @Autowired
    private ArticleServiceCRUD articleServiceCRUD;


    public Article findArticleById(Integer articleId){
        return articleRepository.findById(articleId).orElse(null);
    }

    public List<Article> findAllArticle(){
        return articleRepository.findAll();
    }

    public List<ArticleDTO> getFullArticles(){
        List<Article> articles = findAllArticle();
        return articleResponseService.getFullArticles(articles);
    }

    public Article addArticleDTO(ArticleAddDTO articleDto){
       return articleServiceCRUD.addArticleDTO(articleDto);
    }

    @Transactional
    public Article editFullArticle(ArticleEditDTO articleEditDTO, final Integer articleId){
       return articleServiceCRUD.editFullArticle(articleEditDTO,articleId);
    }

    public void deleteArticleById(Integer articleId){
        articleRepository.deleteById(articleId);
        throw new ApiRequestException("message", 1001);
    }


}
