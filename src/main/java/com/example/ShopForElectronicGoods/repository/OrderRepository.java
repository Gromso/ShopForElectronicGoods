package com.example.ShopForElectronicGoods.repository;


import com.example.ShopForElectronicGoods.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.cart.cart_id = :cartId")
    List<Orders> getOrderByCartId(@Param("cartId") Integer cartId);
}
