package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    Optional<Article> getArticleByName(String name);



    @Query("SELECT a FROM Article a WHERE a.category.categoryId = :categoryId")
    List<Article> findByCategoryId(@Param("categoryId") Integer categoryId);

    @Query("SELECT a FROM Article a " +
            "JOIN CartArticle ca ON a.article_id = ca.article.article_id " +
            "WHERE a.article_id = :articleId AND ca.cart_article_id = :cartArticleId")
    Article findArticleByArticleIdAndCartArticleId(@Param("articleId") Integer articleId, @Param("cartArticleId") Integer cartArticleId);

    @Query("SELECT p FROM Photo p WHERE p.article = :article")
    List<Photo> foundPhotosByArticle(@Param("article") Article article);

    @Query("SELECT ap FROM ArticlePrice ap WHERE ap.article = :article")
    List<ArticlePrice> foundListArticlePrices(@Param("article") Article article);

    @Query("SELECT af FROM ArticleFeature af WHERE af.article = :article")
    List<ArticleFeature> foundListArticleFeature(@Param("article") Article article);

    @Query("SELECT af.feature FROM ArticleFeature af WHERE af.article.id = :articleId")
    List<Feature> findFeaturesByArticleId(@Param("articleId") Integer articleId);
}
