package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.CartArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartArticleRepository extends JpaRepository<CartArticle,Integer> {
}
