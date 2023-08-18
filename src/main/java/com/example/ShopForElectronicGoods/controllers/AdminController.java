package com.example.ShopForElectronicGoods.controllers;



import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {


    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationUser>> getAllUser() throws ApiRequestException{
            List<ApplicationUser> user = userService.getAllUser();
            return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email){
        try{
            UserDetails user = userService.loadUserByUsername(email);
            return ResponseEntity.ok(user);
        }catch (ApiRequestException e){
            throw new ApiRequestException("email not valid");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
    }
}
