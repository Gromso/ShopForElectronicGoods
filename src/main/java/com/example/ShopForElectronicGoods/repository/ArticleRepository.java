package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    Optional<Article> getArticleByName(String name);

}
