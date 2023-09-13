package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.models.CartArticle;
import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.AddArticleToCartDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Cart.CartResponseDTO;
import com.example.ShopForElectronicGoods.modelsDTO.Orders.OrdersResponseDTO;
import com.example.ShopForElectronicGoods.services.CartServices.CartService;
import com.example.ShopForElectronicGoods.services.CartServices.CartServiceForMethod;
import com.example.ShopForElectronicGoods.services.CartServices.CartServicesForActiveCart;
import com.example.ShopForElectronicGoods.services.OrderService.OrderMailerService;
import com.example.ShopForElectronicGoods.services.OrderService.OrderService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cart")
public class UserCartController {


    @Autowired
    private CartService cartService;

    @Autowired
    private CartServicesForActiveCart cartServicesForActiveCart;

    @Autowired
    private CartServiceForMethod cartServiceForMethod;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMailerService orderMailerService;


    @GetMapping("")

    public ResponseEntity<CartResponseDTO> getCurrentCart (@AuthenticationPrincipal Jwt principal) {
        Long  userId = principal.getClaim("user_id");
        CartResponseDTO cart = cartServiceForMethod.getActiveCartForUserId(userId);
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/addToCart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponseDTO> addArticlesToCart(@Valid @RequestBody AddArticleToCartDTO body,
                                                             @AuthenticationPrincipal Jwt principal){

        Long  userId = principal.getClaim("user_id");
        CartResponseDTO cart = cartServiceForMethod.getActiveCartForUserId(userId);

       Cart cart2 = cartService.addArticleToCart(cart.getCart_id(),body.getArticle_id(), body.getQuantity());
        CartResponseDTO response = cartServiceForMethod.getListCartArticles(cart2.getCart_id(),Math.toIntExact(userId));
       return  new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/makeOrder")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrdersResponseDTO> makeOrder(@AuthenticationPrincipal Jwt principal) throws MessagingException {
        Long  userId = principal.getClaim("user_id");
        Cart cart =  cartServicesForActiveCart.getLastActiveCartByUserId(Math.toIntExact(userId));
        if(cart == null) {
           throw new ApiRequestException("Cart by cart id is null", HttpStatus.NOT_FOUND);
        }
        Orders orders = orderService.addOrderByCartId(cart.getCart_id());

        OrdersResponseDTO ordersResponseDTO = cartServiceForMethod.getOrdersResponse(cart.getCart_id(),
                Math.toIntExact(userId), orders.getOrder_id());

        /*orderMailerService.sendHtmlEmail("some one", "ovo je subject", " ovo je bodt");

      String message = orderMailerService.htmlBody(ordersResponseDTO);
        orderMailerService.sendHtmlEmail(ordersResponseDTO.getCart().getUser().getEmail(),
                "Purchase confirmation",
                message);*/

      return new ResponseEntity<>(ordersResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponseDTO > updateQuantityForArticle( @RequestBody AddArticleToCartDTO data,
                                         @AuthenticationPrincipal Jwt principal){
        Long  userId = principal.getClaim("user_id");
        CartResponseDTO cart = cartServiceForMethod.getActiveCartForUserId(userId);
        CartArticle ca = cartService.updateQuantityForArticle(cart.getCart_id(),data.getArticle_id(),data.getQuantity());
        CartResponseDTO response = cartServiceForMethod.getListCartArticles(ca.getCart().getCart_id(),Math.toIntExact(userId));
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponseDTO> deleteCartArticleForArticle(@RequestBody AddArticleToCartDTO data, @AuthenticationPrincipal Jwt principal){
        Long  userId = principal.getClaim("user_id");
        CartResponseDTO cart = cartServiceForMethod.getActiveCartForUserId(userId);
        cartService.deleteCartArticleForArticle(cart.getCart_id(), data.getArticle_id());
        CartResponseDTO response = cartServiceForMethod.getListCartArticles(cart.getCart_id(),Math.toIntExact(userId));
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

}