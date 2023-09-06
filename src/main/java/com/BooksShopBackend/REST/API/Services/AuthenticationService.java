package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.models.*;
import com.BooksShopBackend.REST.API.models.Errors.ApplicationError;
import com.BooksShopBackend.REST.API.repository.RoleRepository;
import com.BooksShopBackend.REST.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
//Transakcje są mechanizmem, który pozwala grupować operacje na bazie danych w jedną jednostkę,
//co gwarantuje, że albo wszystkie operacje zostaną wykonane poprawnie, albo żadna z nich nie zostanie wykonana.
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public RegistrationResponseDTO registerUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser registeredUser = userRepository.save(new ApplicationUser(0, username, email, encodedPassword, authorities));

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

        String idToken = tokenService.generateJwt(auth);
        String refreshToken = tokenService.generateRefreshToken();

        RegistrationResponseDTO responseDTO = new RegistrationResponseDTO();
        responseDTO.setUserId(registeredUser.getUserId().toString());
        responseDTO.setUsername(registeredUser.getUsername());
        responseDTO.setIdToken(idToken);
        responseDTO.setRefreshToken(refreshToken);

        return responseDTO;
    }

    public LoginResponseDTO loginUser(String email, String password) {
        Authentication auth = authenticateUser(email, password);
        ApplicationUser loginUser = getUserByEmail(email);

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setUserId(loginUser.getUserId().toString());
        responseDTO.setUsername(loginUser.getUsername());
        responseDTO.setIdToken(tokenService.generateJwt(auth));
        responseDTO.setRefreshToken(tokenService.generateRefreshToken());

        return responseDTO;
    }

    private Authentication authenticateUser(String email, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new ApplicationError("Invalid email or password");
        }
    }

    private ApplicationUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationError("User not found for email: " + email));
    }


    public boolean deleteUser(Integer userId){
            Optional<ApplicationUser> userByUserId = userRepository.findByUserId(userId);
            if (userByUserId.isPresent()) {
                userRepository.delete(userByUserId.get());
                return true;
            }
            return false;
        }
}
