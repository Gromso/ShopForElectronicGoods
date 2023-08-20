package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    @Query("SELECT c FROM Cart c WHERE c.user.user_id = :userId")
    List<Cart> findCartByUserId(@Param("userId") Integer userId);
}
