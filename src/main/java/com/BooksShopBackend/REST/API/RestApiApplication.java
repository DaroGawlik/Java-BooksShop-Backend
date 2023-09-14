package com.BooksShopBackend.REST.API;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.dataBase.UserRole;
import com.BooksShopBackend.REST.API.repositories.BookRepository;
import com.BooksShopBackend.REST.API.repositories.RoleRepository;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import com.BooksShopBackend.REST.API.services.books.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.IOException;
import java.io.InputStream;
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
						  PasswordEncoder passwordEncoder) {
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

		};
	}
	@Bean
	CommandLineRunner runner(BookService bookService, BookRepository bookRepository) {
		return args -> {
//			if (bookRepository.countImportedStatus() > 0) {
//				return;
//			}

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<BooksList>> typeReference = new TypeReference<List<BooksList>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/books.json");
			try {
				List<BooksList> booksLists = mapper.readValue(inputStream, typeReference);
				bookService.save(booksLists);
				System.out.println("Books Saved!");
			} catch (IOException e) {
				System.out.println("Unable to save books: " + e.getMessage());
			}
		};
	}


}
