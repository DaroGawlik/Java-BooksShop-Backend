package com.BooksShopBackend.REST.API.security;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpHeaderSecurityFilter http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signup").permitAll() // Pozwól na dostęp do /signup bez uwierzytelniania
                .anyRequest().authenticated() // Wymagaj uwierzytelnienia dla innych endpointów
                .and()
                .csrf().disable(); // Wyłącz ochronę CSRF (Cross-Site Request Forgery)
    }
}