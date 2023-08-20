package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.repository.CartRepository;
import com.example.ShopForElectronicGoods.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;


    public Cart getCartById(Integer cartId){
        return cartRepository.findById(cartId).orElseThrow(() -> new ApiRequestException("cart Id" + cartId + "not found"));
    }

    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public Cart addCart(Cart cart,Integer userId){
        Cart cartUser = userRepository.findById(userId).map(user ->{
            cart.setUser(user);
            return cartRepository.save(cart);
        }).orElseThrow(() -> new ApiRequestException("save failed"));
        return cartRepository.save(cartUser);
    }



}
