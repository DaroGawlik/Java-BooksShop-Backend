package com.BooksShopBackend.REST.API.services;


import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserApplication user = userRepository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}


//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
//    }
//
//    public UserDetails loadUserByUserId(Integer userId) throws UsernameNotFoundException {
//        return userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User is not valid"));
//    }