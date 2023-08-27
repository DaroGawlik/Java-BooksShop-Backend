package com.BooksShopBackend.REST.API.controller;

import com.BooksShopBackend.REST.API.Services.AuthenticationService;
import com.BooksShopBackend.REST.API.models.ApplicationError;
import com.BooksShopBackend.REST.API.models.ApplicationUser;
import com.BooksShopBackend.REST.API.models.LoginResponseDTO;
import com.BooksShopBackend.REST.API.models.RegistrationDTO;
import com.BooksShopBackend.REST.API.utils.ReturnSecureTokenException;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body) {
        if (body.getReturnSecureToken() == null || !body.getReturnSecureToken()) {
            ApplicationError errorResponse = new ApplicationError("Registration with returnSecureToken=false is not allowed");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        ApplicationUser registeredUser = authenticationService.registerUser(body.getUsername(), body.getPassword());
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
