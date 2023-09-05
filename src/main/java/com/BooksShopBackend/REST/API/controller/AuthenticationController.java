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

        try {
            // Wywołaj serwis do obsługi rejestracji i otrzymaj odpowiedź
            RegistrationResponseDTO response = authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword());
            return ResponseEntity.ok(response); // Zwróć sukces i odpowiedź z danymi użytkownika
        } catch (ApplicationError e) {
            // Tutaj możesz obsłużyć błąd związanym z już istniejącym adresem e-mail
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e); // Zwracamy błąd HTTP 409 Conflict
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO body){
        if (body.getReturnSecureToken() == null || !body.getReturnSecureToken()) {
            ApplicationError errorResponse = new ApplicationError("Registration with returnSecureToken=false is not allowed");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        System.out.println("Received password: " + body.getPassword());
        LoginResponseDTO responseDTO = authenticationService.loginUser(body.getEmail(), body.getPassword());
                return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String authorizationHeader) {
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


}






