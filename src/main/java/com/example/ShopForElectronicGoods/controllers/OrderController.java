package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.services.OrderService.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable final Integer orderId){
        Orders orderById = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderById);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/orders")
    public ResponseEntity<List<Orders>> getOrders(){
        List<Orders> orders = orderService.getOrderList();
        return  ResponseEntity.ok(orders);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Orders>> getAllOrders(@AuthenticationPrincipal Jwt principal){
        Long  userId = principal.getClaim("user_id");
        List<Orders> listOrders = orderService.getOrdersByUserId(Math.toIntExact(userId));
        return ResponseEntity.ok(listOrders);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editOrder/{orderId}")
    public ResponseEntity<Orders> editOrder(@Valid @RequestBody Orders order,
                                           @PathVariable final Integer orderId){
        Orders order2 = orderService.editOrder(order,orderId);
        return ResponseEntity.ok(order2);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable final Integer orderId){
       orderService.deleteOrderById(orderId);
    }


}
