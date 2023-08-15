package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.ArticlePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePriceRepository extends JpaRepository<ArticlePrice, Integer> {

}
