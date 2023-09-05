package com.BooksShopBackend.REST.API.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsEmailAdapter implements UserDetailsService {

    private final UserDetailsEmail userDetailsEmail;

    public UserDetailsEmailAdapter(UserDetailsEmail userDetailsEmail) {
        this.userDetailsEmail = userDetailsEmail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tu użyj UserDetailsEmail do wczytania użytkownika na podstawie adresu e-mail
        return userDetailsEmail.loadUserByEmail(username);
    }
}