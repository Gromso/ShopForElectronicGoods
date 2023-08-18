package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.Role;
import com.example.ShopForElectronicGoods.modelsDTO.LoginResponseDTO;
import com.example.ShopForElectronicGoods.repository.RoleRepository;
import com.example.ShopForElectronicGoods.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public ApplicationUser registerUser(String email, String password, String forename,String surname, String phone_number, String postal_address ){
        String encodePassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").orElse(null);

        Set<Role> authority= new HashSet<>();
        authority.add(userRole);

        return userRepository.save(new ApplicationUser(email,encodePassword,authority, forename, surname, phone_number,postal_address));

    }

   /* public LoginResponseDTO login(String email, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByEmail(email).orElse(null), token);
        }catch(Exception e){
           return new LoginResponseDTO(null, "");
        }
    }*/
   public LoginResponseDTO login(String email, String password){
       try {
           Authentication auth = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(email, password)
           );
           String token = tokenService.generateJwt(auth);
           // Pronalaženje korisnika samo jednom prilikom autentifikacije
           if(auth != null){
               ApplicationUser user = (ApplicationUser) auth.getPrincipal();
               return new LoginResponseDTO(user, token);
           }else {
               throw new ApiRequestException("message");
           }

       } catch (ApiRequestException e) {
           return new LoginResponseDTO(null, "");
       }
   }
}
