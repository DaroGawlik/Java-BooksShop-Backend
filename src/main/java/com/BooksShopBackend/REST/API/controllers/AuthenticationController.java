package com.BooksShopBackend.REST.API.controllers;

import com.BooksShopBackend.REST.API.Services.AuthenticationService;
import com.BooksShopBackend.REST.API.Services.TokenService;
import com.BooksShopBackend.REST.API.models.*;
import com.BooksShopBackend.REST.API.models.Auth.LoginDTO;
import com.BooksShopBackend.REST.API.models.Auth.LoginResponseDTO;
import com.BooksShopBackend.REST.API.models.Auth.RegistrationDTO;
import com.BooksShopBackend.REST.API.models.Auth.RegistrationResponseDTO;
import com.BooksShopBackend.REST.API.models.Errors.ApplicationError;
import com.BooksShopBackend.REST.API.models.Errors.InvalidCredentialsError;
import com.BooksShopBackend.REST.API.models.Errors.UserNotFoundError;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body) {
        if (body.getReturnSecureToken() == null || !body.getReturnSecureToken()) {
            ApplicationError errorResponse = new ApplicationError("Registration with returnSecureToken = false is not allowed");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Optional<UserApplication> existingUserOptional = userRepository.findByEmail(body.getEmail());
        if (existingUserOptional.isPresent()) {
            ApplicationError errorResponse = new ApplicationError("This email " + body.getEmail() + " is already registered");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        try {
            RegistrationResponseDTO response = authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword());
            return ResponseEntity.ok(response);
        } catch (ApplicationError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO body) {
        if (body.getReturnSecureToken() == null || !body.getReturnSecureToken()) {
            return ResponseEntity.badRequest().body(new ApplicationError("Login with returnSecureToken = false is not allowed"));
        }
        try {
            LoginResponseDTO responseDTO = authenticationService.loginUser(body.getEmail(), body.getPassword());
            return ResponseEntity.ok(responseDTO);
        } catch (InvalidCredentialsError e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        } catch (UserNotFoundError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (ApplicationError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
        Optional<UserApplication> userByUserId = userRepository.findByUserId(userId);

        if (userByUserId.isPresent()) {
            userRepository.delete(userByUserId.get());
            return ResponseEntity.ok("The user has been successfully deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with the provided ID was not found.");
    }
}






