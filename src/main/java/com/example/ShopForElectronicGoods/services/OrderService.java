package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

}
