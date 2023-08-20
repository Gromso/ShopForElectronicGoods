package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.ArticleFeature;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface ArticleFeatureRepository extends JpaRepository<ArticleFeature, Integer> {

    Optional<ArticleFeature> findArticleFeatureByValue(String value);

   // @Query("SELECT af FROM ArticleFeature af WHERE af.article.article_id = :articleId")
   // List<ArticleFeature> findArticleFeatureByArticleId(@Param("articleId") Integer articleId);
   @Query("SELECT af.feature FROM ArticleFeature af " +
           "WHERE af.article.article_id = :articleId")
    List<Feature> findArticleFeatureByArticleId(@Param("articleId") Integer articleId);
}
