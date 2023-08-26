package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.Article;
import com.example.ShopForElectronicGoods.models.Cart;

import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.AddArticleToCartDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.repository.ArticleRepository;
import com.example.ShopForElectronicGoods.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
public class UserCartController {




    @Autowired
    private CartService cartService;

    @Autowired
    private ArticleRepository articleRepository;


    private Cart getActiveCartForUserId(Long userId){
        Cart userCart = cartService.getLastActiveCartByUserId(Math.toIntExact(userId));
        Orders orderCart = null;
        if(userCart != null ) {
             orderCart = cartService.getOrderByCartId(userCart.getCart_id());
        }
        if(userCart == null || orderCart != null){
            userCart =   cartService.createNewCartForUser(Math.toIntExact(userId));
        }

        return cartService.getCartById(userCart.getCart_id());
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cart> getCurrentCart (@AuthenticationPrincipal Jwt principal) {
        Long  userId = principal.getClaim("user_id");
         Cart cart = getActiveCartForUserId(userId);
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/addToCart")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CartResponseDTO> addArticlesToCart(@RequestBody AddArticleToCartDTO body,@AuthenticationPrincipal Jwt principal){

        Long  userId = principal.getClaim("user_id");
        Cart cart = getActiveCartForUserId(userId);

       Cart cart2 = cartService.addArticleToCart(cart.getCart_id(),body.getArticle_id(), body.getQuantity());
        CartResponseDTO response = cartService.getListCartArticles(cart2.getCart_id(),Math.toIntExact(userId));
       return  new ResponseEntity<>(response, HttpStatus.OK);
    }



}