package com.example.ShopForElectronicGoods.controllers;


import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.Cart;
import com.example.ShopForElectronicGoods.modelsDTO.RegistrationDTO;
import com.example.ShopForElectronicGoods.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String helloUser(){
        return "Hello user";
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<ApplicationUser> editUserById(@Valid @RequestBody RegistrationDTO data, @PathVariable Integer id){
        try{
            ApplicationUser user = userService.editUserById(data, id);
            return ResponseEntity.ok(user);
        }catch (Exception e){
          return   ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/{userId}/user")
    public ResponseEntity<List<Cart>> findCartByUserId(@PathVariable final Integer userId){
        List<Cart> carts = userService.findCartByUser(userId);
        return new ResponseEntity<>(carts, HttpStatus.FOUND);
    }
}
