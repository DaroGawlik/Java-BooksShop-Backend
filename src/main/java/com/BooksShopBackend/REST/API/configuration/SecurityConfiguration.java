package com.BooksShopBackend.REST.API.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfiguration {

    public SecurityFilterChain filterChain(HttpSecurity http){}
}
