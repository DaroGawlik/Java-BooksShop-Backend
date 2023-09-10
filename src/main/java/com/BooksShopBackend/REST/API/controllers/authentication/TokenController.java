package com.BooksShopBackend.REST.API.controllers.authentication;

import com.BooksShopBackend.REST.API.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@CrossOrigin("*")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity<String> generateRefreshToken() {
        String refreshToken = tokenService.generateRefreshToken();
        return ResponseEntity.ok(refreshToken);
    }
}