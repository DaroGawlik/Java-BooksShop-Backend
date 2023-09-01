package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.utils.RSAKeyProperties;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
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

    public String generateJwt(String username, Collection<? extends GrantedAuthority> authorities, long expirationInSeconds) {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(expirationInSeconds); // Ustawić czas wygaśnięcia na podstawie podanej liczby sekund.

        String scope = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String token = Jwts.builder()
                .setIssuer("self")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration)) // Ustawienie czasu wygaśnięcia
                .setSubject(username)
                .claim("roles", scope)
                .signWith(secretKey, SignatureAlgorithm.HS256)
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
