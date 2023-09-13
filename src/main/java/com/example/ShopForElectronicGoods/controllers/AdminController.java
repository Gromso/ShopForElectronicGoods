package com.example.ShopForElectronicGoods.controllers;
import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.Orders;
import com.example.ShopForElectronicGoods.services.OrderService.OrderService;
import com.example.ShopForElectronicGoods.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ApplicationUser>> getAllUser() throws ApiRequestException{
            List<ApplicationUser> user = userService.getAllUser();
            return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email){
        try{
            UserDetails user = userService.loadUserByUsername(email);
            return ResponseEntity.ok(user);
        }catch (ApiRequestException e){
            throw new ApiRequestException("email not valid");
        }
    }


    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Orders> gerOrder(@PathVariable Integer orderId){
        Orders orders = orderService.getOrderById(orderId);

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/order/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Orders> updateOrderForStatus(@RequestBody Orders b,@PathVariable Integer orderId){
        Orders orders = orderService.editOrder(b, orderId);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable Integer id){
        ApplicationUser user = userService.findUserById(id);
        if(user == null){
            throw new ApiRequestException("User by "+ id + " not found", HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        throw new ApiRequestException("User is successfully deleted by "+ id, HttpStatus.OK);

    }
}
