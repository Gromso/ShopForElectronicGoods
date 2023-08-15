package com.example.ShopForElectronicGoods.repository;

import com.example.ShopForElectronicGoods.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
