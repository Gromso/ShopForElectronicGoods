package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.models.Order;
import com.example.ShopForElectronicGoods.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable final Integer orderId){
        Order orderById = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderById);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> listOrders = orderService.getOrderList();
        return ResponseEntity.ok(listOrders);
    }

    @PostMapping("/addOrder/{cartId}")
    public ResponseEntity<Order> addOrder(@RequestBody Order order,
                                          @PathVariable  final Integer cartId){
        Order order2 = orderService.addOrderByCartId(order, cartId);
        return ResponseEntity.ok(order2);
    }

    @PutMapping("/editOrder/{orderId}")
    public ResponseEntity<Order> editOrder(@RequestBody Order order,
                                           @PathVariable final Integer orderId){
        Order order2 = orderService.editOrder(order,orderId);
        return ResponseEntity.ok(order2);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable final Integer orderId){
       orderService.deleteOrderById(orderId);
    }


}
