package com.BooksShopBackend.REST.API;

import com.BooksShopBackend.REST.API.models.dataBase.Books;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.dataBase.UserRole;
import com.BooksShopBackend.REST.API.repositories.BooksRepository;
import com.BooksShopBackend.REST.API.repositories.RoleRepository;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import com.BooksShopBackend.REST.API.utils.JsonParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, UserDetailRepository userDetailRepository,
						  PasswordEncoder passwordEncoder, JsonParser jsonParser, BooksRepository booksRepository) {
		//	CommandLineRunner run która jest wywoływana po uruchomieniu aplikacji Spring Boot. Ten interfejs jest często wykorzystywany
				//	do definiowania kodu, który powinien być wykonany tuż po uruchomieniu aplikacji.
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

			UserRole adminRole = roleRepository.save(new UserRole("ADMIN"));
			roleRepository.save(new UserRole("USER"));
			Set<UserRole> roles = new HashSet<>();
			roles.add(adminRole);

			UserApplication registeredAdmin = userRepository.save(new UserApplication(0, "admin@email.com", passwordEncoder.encode("password"), roles));

			UserApplicationDetails userApplicationDetails = new UserApplicationDetails("admin");
			userApplicationDetails.setUserApplication(registeredAdmin);
			userDetailRepository.save(userApplicationDetails);


			List<Books> books = jsonParser.parseJsonToBooks("classpath:static/books.json");
			books.forEach(book -> {
				System.out.println("Wczytano książkę: " + book.getTitle());
			});
			booksRepository.saveAll(books);
		};
	}
}
