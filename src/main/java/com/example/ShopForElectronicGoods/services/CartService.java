package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
}
