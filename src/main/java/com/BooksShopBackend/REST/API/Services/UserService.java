package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.models.ApplicationUser;
import com.BooksShopBackend.REST.API.models.Role;
import com.BooksShopBackend.REST.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }
    public UserDetails loadUserByUserId(Integer userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
    }

}
