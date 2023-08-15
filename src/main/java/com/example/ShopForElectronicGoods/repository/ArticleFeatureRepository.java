package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.ArticleFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleFeatureRepository extends JpaRepository<ArticleFeature, Integer> {

    Optional<ArticleFeature> findArticleFeatureByValue(String value);
}
