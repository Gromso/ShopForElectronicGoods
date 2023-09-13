package com.example.ShopForElectronicGoods.controllers;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.UserToken;
import com.example.ShopForElectronicGoods.modelsDTO.*;
import com.example.ShopForElectronicGoods.services.AuthenticationService;
import com.example.ShopForElectronicGoods.services.TokenService;
import com.example.ShopForElectronicGoods.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ApplicationUser registration(@Valid @RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getEmail(),body.getPassword(), body.getForename(),body.getSurname(),body.getPhone_number(),body.getPostal_address());
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginDTO body) {
        try{
            LoginResponseDTO user =  authenticationService.login(body.getEmail(),body.getPassword());
            return ResponseEntity.ok(user);
        }catch (ApiRequestException e){
           throw new ApiRequestException("ee", HttpStatus.BAD_REQUEST, -3001);
        }
    }

    @PostMapping("/user/refresh")
    public ResponseEntity<RefreshTokenResponseDTO> userTokenRefresh(@RequestBody UserRefreshTokenDTO body, @AuthenticationPrincipal Jwt principal) throws Exception {
        try {
            UserToken userRefreshToken = tokenService.getTokenByName(body.getToken());
            RefreshTokenResponseDTO responseDTO = tokenService.responseRefreshToken(userRefreshToken.getToken());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (NullPointerException e){
            throw new ApiRequestException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }
}
