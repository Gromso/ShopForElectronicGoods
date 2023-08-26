package com.example.ShopForElectronicGoods.repository;


import com.example.ShopForElectronicGoods.models.CartArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartArticleRepository extends JpaRepository<CartArticle,Integer> {


    @Query("SELECT ca FROM CartArticle ca WHERE ca.cart.cart_id = :cartId")
    List<CartArticle> findCartArticleByCartId(@Param("cartId") Integer cartId);

    @Query("SELECT ca FROM CartArticle ca WHERE ca.cart.cart_id = :cartId AND ca.article.article_id = :articleId")
    CartArticle findCartArticleByCartIdAndArticleId(@Param("cartId") Integer cartId, @Param("articleId") Integer articleId);
}
