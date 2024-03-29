package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.repository.ArticlePriceRepository;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ArticlePriceService {



    @Autowired
    private ArticlePriceRepository articlePriceRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private  EntityManager entityManager;

    public ArticlePrice getArticlePriceById(Integer articlePriceId){
        return articlePriceRepository.findById(articlePriceId).orElseThrow(
                () -> new ApiRequestException("ArticlePrice with articlePriceId" + articlePriceId +" not found", HttpStatus.NOT_FOUND));
    }

    public List<ArticlePrice> getAllArticlePrice(){
        return articlePriceRepository.findAll();
    }

   public ArticlePrice addArticlePrice(ArticlePrice articlePrice, Integer articleId) {
        ArticlePrice articleForAp = articleRepository.findById(articleId).map(aricle ->{
            articlePrice.setArticle(aricle);
            return articlePriceRepository.save(articlePrice);
        }).orElseThrow(() -> new ApiRequestException("Article with id" + articleId + " not found"));
        return articlePriceRepository.save(articleForAp);
    }

    public ArticlePrice editArticlePrice(ArticlePrice articlePrice, Integer articleId){
        ArticlePrice articlePricee = getArticlePriceById(articleId);

        if(Objects.nonNull(articlePrice.getPrice())){
            articlePricee.setPrice(articlePrice.getPrice());
        }

        return articlePriceRepository.save(articlePricee);

    }

    public void deleteArticlePriceById(Integer articlePriceId){
         articlePriceRepository.deleteById(articlePriceId);
    }



}
