package com.BooksShopBackend.REST.API.controller;

import com.BooksShopBackend.REST.API.models.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @CrossOrigin("*")
    public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        System.out.println("Received signup request: " + signupRequest.toString());
        return ResponseEntity.ok("użytkownik zarejestrowany");
    }
    }

