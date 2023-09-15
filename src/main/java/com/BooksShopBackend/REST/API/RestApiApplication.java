package com.BooksShopBackend.REST.API;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.dataBase.UserRole;
import com.BooksShopBackend.REST.API.repositories.BookListRepository;
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
		CommandLineRunner run(BookService bookService, BookListRepository bookListRepository, RoleRepository roleRepository, UserRepository userRepository,
							  UserDetailRepository userDetailRepository, PasswordEncoder passwordEncoder
		) {
			return args -> {
				// Importuj książki, jeśli nie istnieją
				if (bookListRepository.count() == 0) {
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
				} else {
					System.out.println("Books already exist in the database. Skipping JSON import...");
				}

				// Utwórz rolę ADMIN, jeśli nie istnieje, i dodaj użytkownika admina
				if (!roleRepository.findByAuthority("ADMIN").isPresent()) {
					UserRole adminRole = roleRepository.save(new UserRole("ADMIN"));
					roleRepository.save(new UserRole("USER"));
					Set<UserRole> roles = new HashSet<>();
					roles.add(adminRole);

					UserApplication registeredAdmin = userRepository.save(
							new UserApplication(0, "admin@email.com", passwordEncoder.encode("password"), roles)
					);

					UserApplicationDetails userApplicationDetails = new UserApplicationDetails("admin");
					userApplicationDetails.setUserApplication(registeredAdmin);
					userDetailRepository.save(userApplicationDetails);
				}
			};
		}
	}
