package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {


    @Query("SELECT p FROM Photo p " +
            "JOIN Article a ON p.article.article_id = a.article_id " +
            "WHERE p.article.article_id = :articleId")
    List<Photo> findPhotosByArticleId(@Param("articleId")Integer articleId);
}
