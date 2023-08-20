package com.example.ShopForElectronicGoods.services.ArticleServices;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.ArticlePrice;
import com.example.ShopForElectronicGoods.repository.ArticleFeatureRepository;
import com.example.ShopForElectronicGoods.repository.ArticlePriceRepository;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticleArticlePriceService {



    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ArticlePriceRepository articlePriceRepository;


   /* @Transactional
    public Article getArticleWithArticlePrices(Integer articleId){
        Article a = articleRepository.findById(articleId).orElseThrow(() -> new ApiRequestException(""));
        Set<ArticlePrice> set = new HashSet<>();
        for(ArticlePrice aa : a.getArticlePrice()) {
            set.add(aa);;
        }
        a.setArticlePrice(set);
        System.out.println(a.getArticlePrice());
        System.out.println(set + "eeeeeeeeeeeeeeeeeeeeeeeee");
        return a;
    }*/

    //TODO:  This is a query that returns all its prices for a specific item
    public List<ArticlePrice> getPricesForArticle(Article article) {
        TypedQuery<ArticlePrice> query = entityManager.createQuery(
                "SELECT ap FROM ArticlePrice ap  WHERE ap.article = :article", ArticlePrice.class
        );
        query.setParameter("article", article);
        return query.getResultList();
    }

    //TODO:  This is a query that returns all its prices for a specific item
    public List<ArticlePrice> getPricesForArticlee(Article article) {
        List<ArticlePrice> allPrices = articlePriceRepository.findAll(); // Dohvati sve cijene

        List<ArticlePrice> articlePrices = new ArrayList<>();
        // Iteriraj kroz sve cijene i provjeri za svaku cijenu pripada li određenom članku
        for (ArticlePrice price : allPrices) {
            if (price.getArticle().equals(article)) {
                articlePrices.add(price);
            }
        }
        return articlePrices;
    }

    //TODO:  This is a query that returns the latest price for a specific item
    public Article findArticleWithLatestPrice(Article article) {
        TypedQuery<Article> query = entityManager.createQuery(
                "SELECT a FROM Article a " +
                        "WHERE a.article_id = :articleId " +
                        "AND a.article_id IN (" +
                        "   SELECT ap.article.article_id " +
                        "   FROM ArticlePrice ap " +
                        "   WHERE ap.article = :article " +
                        "   ORDER BY ap.created_at_price DESC " +
                        ")", Article.class
        );
        query.setParameter("articleId", article.getArticle_id());
        query.setParameter("article", article);

        List<Article> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }


}
