package com.example.ShopForElectronicGoods;

import com.example.ShopForElectronicGoods.models.ApplicationUser;
import com.example.ShopForElectronicGoods.models.Role;
import com.example.ShopForElectronicGoods.repository.RoleRepository;
import com.example.ShopForElectronicGoods.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ShopForElectronicGoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopForElectronicGoodsApplication.class, args);
	}
}
