package com.BooksShopBackend.REST.API;

import com.BooksShopBackend.REST.API.models.UserApplication;
import com.BooksShopBackend.REST.API.models.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.UserRole;
import com.BooksShopBackend.REST.API.repositories.RoleRepository;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run (RoleRepository roleRepository, UserRepository userRepository, UserDetailRepository userDetailRepository, PasswordEncoder passwordEncoder) {
		//	CommandLineRunner run która jest wywoływana po uruchomieniu aplikacji Spring Boot. Ten interfejs jest często wykorzystywany
		//	do definiowania kodu, który powinien być wykonany tuż po uruchomieniu aplikacji.
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			UserRole adminRole = roleRepository.save(new UserRole("ADMIN"));
			roleRepository.save(new UserRole("USER"));
			Set<UserRole> roles = new HashSet<>();
			roles.add(adminRole);

			UserApplication admin = new UserApplication(1, "admin@email.com",passwordEncoder.encode("password"), roles);
			UserApplicationDetails adminDetail = new UserApplicationDetails("admin");
			userRepository.save(admin);
			userDetailRepository.save(adminDetail);
		};
	}
}
