package com.BooksShopBackend.REST.API;

import com.BooksShopBackend.REST.API.models.ApplicationUser;
import com.BooksShopBackend.REST.API.models.Role;
import com.BooksShopBackend.REST.API.repository.RoleRepository;
import com.BooksShopBackend.REST.API.repository.UserRepository;
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
	CommandLineRunner run (RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		//	CommandLineRunner run która jest wywoływana po uruchomieniu aplikacji Spring Boot. Ten interfejs jest często wykorzystywany
		//	do definiowania kodu, który powinien być wykonany tuż po uruchomieniu aplikacji.
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			Role userRole = roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}
}
