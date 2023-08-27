package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.CartArticle;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartArticleDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.repository.CartArticleRepository;
import com.example.ShopForElectronicGoods.repository.CartRepository;
import com.example.ShopForElectronicGoods.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartArticleRepository cartArticleRepository;

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<CartResponseDTO> getCartWithArticles(@PathVariable Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ApiRequestException("Cart with ID " + cartId + " not found"));

        List<CartArticle> cartArticles = cartArticleRepository.findCartArticleByCartId(cartId);

        if (cartArticles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }




        // Mapiranje na DTO objekte
        List<CartArticleDTO> cartArticleDTOs = cartArticles.stream()
                .map(cartArticle -> {
                    CartArticleDTO dto = new CartArticleDTO();
                    dto.setCartArticle_id(cartArticle.getCart_article_id());
                    dto.setCart_id(cartArticle.getCart().getCart_id());
                    dto.setArticle_id(cartArticle.getArticle().getArticle_id());
                    dto.setQuantity(cartArticle.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());

        // Kreiranje odgovarajućeg JSON odgovora
        CartResponseDTO responseDTO = new CartResponseDTO();
        responseDTO.setCart_id(cart.getCart_id());
        responseDTO.setCartArticles(cartArticleDTOs);

        return ResponseEntity.ok(responseDTO);
    }

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
