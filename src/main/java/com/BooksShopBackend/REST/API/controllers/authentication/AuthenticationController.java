package com.BooksShopBackend.REST.API.controllers.authentication;

import com.BooksShopBackend.REST.API.services.AuthenticationService;
import com.BooksShopBackend.REST.API.models.auth.LoginDTO;
import com.BooksShopBackend.REST.API.models.auth.LoginResponseDTO;
import com.BooksShopBackend.REST.API.models.auth.RegistrationDTO;
import com.BooksShopBackend.REST.API.models.auth.RegistrationResponseDTO;
import com.BooksShopBackend.REST.API.models.errors.ApplicationError;
import com.BooksShopBackend.REST.API.models.errors.InvalidCredentialsError;
import com.BooksShopBackend.REST.API.models.errors.UserNotFoundError;
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
            ApplicationError errorResponse = new ApplicationError("Registration with returnSecureToken = false is not allowed");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        try {
            RegistrationResponseDTO response = authenticationService.registerUser(body.getUserName(), body.getEmail(), body.getPassword());
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
        if (authenticationService.deleteUser(userId)) {
            return ResponseEntity.ok("The user and their details have been successfully deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with the provided ID was not found.");
    }

}







