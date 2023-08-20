package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.modelsDTO.CartDTO;
import com.example.ShopForElectronicGoods.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;


    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> findCartById(@PathVariable final Integer cartId){
        Cart cart = cartService.getCartById(cartId);
        return new ResponseEntity<>(cart, HttpStatus.ACCEPTED);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> findListCart(){
        List<Cart> carts = cartService.getAllCart();
        return new ResponseEntity<>(carts, HttpStatus.FOUND);
    }

    @PostMapping("/add/{userId}/user")
    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO body, @PathVariable final Integer userId){

        Cart cart = cartService.addCart(Cart.from(body), userId);
        return new ResponseEntity<>(CartDTO.from(cart), HttpStatus.CREATED);
    }

}
