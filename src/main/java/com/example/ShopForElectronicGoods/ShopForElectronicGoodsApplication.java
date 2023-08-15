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

	@Bean
	CommandLineRunner run(RoleRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (repository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = repository.save(new Role("ADMIN"));
			repository.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			ApplicationUser admin = new ApplicationUser("admin123@gmail.com", passwordEncoder.encode("password"), roles,"Ivan","Ivic","05123424","Novi Sad 123");
			userRepository.save(admin);

		};

	}
}
