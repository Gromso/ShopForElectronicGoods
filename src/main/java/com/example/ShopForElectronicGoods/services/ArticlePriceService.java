package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.repository.ArticlePriceRepository;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlePriceService {



    @Autowired
    private ArticlePriceRepository articlePriceRepository;

    @Autowired
    private ArticleRepository articleRepository;

   public ArticlePrice addArticlePrice(ArticlePrice articlePrice, Integer articleId) {
        ArticlePrice articleForAp = articleRepository.findById(articleId).map(aricle ->{
            articlePrice.setArticle(aricle);
            return articlePriceRepository.save(articlePrice);
        }).orElseThrow(() -> new ApiRequestException("Article with id" + articleId + " not found"));
        return articlePriceRepository.save(articleForAp);
    }

    //TODO:  Ovo je drugi nacin na koji mozemo da dodajemo decu roditeljima.
    /*Article article = // dohvatite Article objekt
ArticlePrice articlePrice = new ArticlePrice();
articlePrice.setPrice(120.00);
articlePrice.setCreatedAtPrice(new Date());
articlePrice.setArticle(article); // Postavite Article
article.getArticlePrice().add(articlePrice); // Dodajte ArticlePrice u set*/
}
