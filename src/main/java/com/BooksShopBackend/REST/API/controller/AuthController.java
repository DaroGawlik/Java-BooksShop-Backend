package com.BooksShopBackend.REST.API.controller;

import com.BooksShopBackend.REST.API.models.SignupRequest;
import com.BooksShopBackend.REST.API.responses.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin("*")
    public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest) {
        System.out.println("Received signup request: " + signupRequest.toString());

        AuthResponse response = new AuthResponse();
        response.setToken("wygenerowany_token");
        response.setExpiresIn(3600L);
        response.setEmail(signupRequest.getEmail());

        return ResponseEntity.ok(response);
    }
}
