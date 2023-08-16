package com.example.ShopForElectronicGoods.services;

import com.example.ShopForElectronicGoods.Exception.ApiRequestException;
import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.modelsDTO.RegistrationDTO;
import com.example.ShopForElectronicGoods.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ApiRequestException {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public List<ApplicationUser> getAllUser() throws ApiRequestException{
        return userRepository.findAll();
    }
    public ApplicationUser findUserById(Integer id){
            return userRepository.findById(id).orElseThrow(()-> new ApiRequestException("user with id" + id + " not found"));
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
        throw new ApiRequestException("User with " + id + " id not found");
    }

    public ApplicationUser editUserById(RegistrationDTO data, Integer id) {
        ApplicationUser user = findUserById(id);
        if (user == null) {
            throw new ApiRequestException("user with id " + id + "not found");
        } else {
            if (Objects.nonNull(data.getPassword()) && !"".equalsIgnoreCase(data.getPassword())) {
                String password = passwordEncoder.encode(data.getPassword());
                user.setPassword_hash(password);
            }
            if (Objects.nonNull(data.getForename()) && !"".equalsIgnoreCase(data.getForename())) {
                user.setForename(data.getForename());
            }
            if (Objects.nonNull(data.getSurname()) && !"".equalsIgnoreCase(data.getSurname())) {
                user.setSurname(data.getSurname());
            }
            if (Objects.nonNull(data.getPhone_number()) && !"".equalsIgnoreCase(data.getPhone_number())) {
                user.setPhone_number(data.getPhone_number());
            }
            if (Objects.nonNull(data.getPostal_address()) && !"".equalsIgnoreCase(data.getPostal_address())) {
                user.setPostal_address(data.getPostal_address());
            }

            return userRepository.save(user);
        }
    }
}
