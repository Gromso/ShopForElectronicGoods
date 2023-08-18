package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    Optional<Article> getArticleByName(String name);

    Set<Article> findByCategory(Category category);

    @Query("SELECT a FROM Article a WHERE a.category.category_id = :categoryId")
    List<Article> findByCategoryId(@Param("categoryId") Integer categoryId);
}
