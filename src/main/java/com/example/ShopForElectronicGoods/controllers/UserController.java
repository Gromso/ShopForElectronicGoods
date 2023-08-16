package com.example.ShopForElectronicGoods.controllers;


import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.modelsDTO.RegistrationDTO;
import com.example.ShopForElectronicGoods.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String helloUser(){
        return "Hello user";
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<ApplicationUser> editUserById(@RequestBody RegistrationDTO data, @PathVariable Integer id){
        try{
            ApplicationUser user = userService.editUserById(data, id);
            return ResponseEntity.ok(user);
        }catch (ApiRequestException e){
            throw new ApiRequestException("User with id not found");
        }
    }
}
