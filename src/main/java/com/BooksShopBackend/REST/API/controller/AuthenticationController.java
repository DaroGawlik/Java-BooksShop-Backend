package com.BooksShopBackend.REST.API.controller;

import com.BooksShopBackend.REST.API.Services.AuthenticationService;
import com.BooksShopBackend.REST.API.Services.TokenService;
import com.BooksShopBackend.REST.API.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body) {
        if (body.getReturnSecureToken() == null || !body.getReturnSecureToken()) {
            ApplicationError errorResponse = new ApplicationError("Registration with returnSecureToken=false is not allowed");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        RegistrationResponseDTO responseDTO = authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword());
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("Received DELETE request for user ID: " + userId);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            ApplicationError errorResponse = new ApplicationError("Missing or invalid authorization token.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
            boolean deleted = authenticationService.deleteUser(userId);

        if (deleted) {
            return ResponseEntity.ok("The user has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with the provided ID was not found.");
        }
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}






