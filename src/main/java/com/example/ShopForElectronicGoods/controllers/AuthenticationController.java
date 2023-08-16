package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.modelsDTO.LoginResponseDTO;
import com.example.ShopForElectronicGoods.modelsDTO.RegistrationDTO;
import com.example.ShopForElectronicGoods.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ApplicationUser registration(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getEmail(),body.getPassword(), body.getForename(),body.getSurname(),body.getPhone_number(),body.getPostal_address());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody RegistrationDTO body) {
        try{
            LoginResponseDTO user =  authenticationService.login(body.getEmail(),body.getPassword());
            return ResponseEntity.ok(user);
        }catch (ApiRequestException e){
           return ResponseEntity.notFound().build();
        }
    }
}
