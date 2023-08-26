package com.example.ShopForElectronicGoods.controllers;


import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        List<Orders> listOrders = orderService.getOrderList();
        return ResponseEntity.ok(listOrders);
    }

    @PostMapping("/addOrder/{cartId}")
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order,
                                          @PathVariable  final Integer cartId){
        Orders order2 = orderService.addOrderByCartId(order, cartId);
        return ResponseEntity.ok(order2);
    }

    @PutMapping("/editOrder/{orderId}")
    public ResponseEntity<Orders> editOrder(@RequestBody Orders order,
                                           @PathVariable final Integer orderId){
        Orders order2 = orderService.editOrder(order,orderId);
        return ResponseEntity.ok(order2);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable final Integer orderId){
       orderService.deleteOrderById(orderId);
    }


}
