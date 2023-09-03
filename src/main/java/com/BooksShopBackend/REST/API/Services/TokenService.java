package com.BooksShopBackend.REST.API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import java.time.Instant;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;



@Service
public class TokenService {
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    private final SecretKey secretKey;

    @Autowired
    public TokenService() {
        // Generuj klucz tajny tylko raz i zachowaj go
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateJwt(String username, Collection<? extends GrantedAuthority> authorities, long expirationInSeconds) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(expirationInSeconds);

        String scope = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String token = Jwts.builder()
                .setIssuer("self")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .setSubject(username)
                .claim("roles", scope)
                .signWith(secretKey, SignatureAlgorithm.HS256) // Użyj zachowanego klucza
                .compact();

        return token;
    }



    public String generateRefreshToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] refreshTokenBytes = new byte[64];
        secureRandom.nextBytes(refreshTokenBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(refreshTokenBytes);
    }
}
