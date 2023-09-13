package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.CartArticle;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.repository.CartArticleRepository;
import com.example.ShopForElectronicGoods.repository.CartRepository;
import com.example.ShopForElectronicGoods.services.CartServices.CartService;
import com.example.ShopForElectronicGoods.services.CartServices.CartServiceForMethod;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private CartServiceForMethod cartServiceForMethod;

    @GetMapping("/cart/{cartId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CartResponseDTO> getCartWithArticles(@PathVariable Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ApiRequestException("Cart with ID " + cartId + " not found"));
        CartResponseDTO responseDTO = cartServiceForMethod.getListCartArticles(cartId, cart.getUser().getUser_id());
        return ResponseEntity.ok(responseDTO);
    }


}
