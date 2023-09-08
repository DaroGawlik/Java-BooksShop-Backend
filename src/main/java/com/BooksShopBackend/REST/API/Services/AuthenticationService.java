package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.models.*;
import com.BooksShopBackend.REST.API.models.Auth.LoginResponseDTO;
import com.BooksShopBackend.REST.API.models.Auth.RegistrationResponseDTO;
import com.BooksShopBackend.REST.API.models.Errors.ApplicationError;
import com.BooksShopBackend.REST.API.repositories.RoleRepository;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
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
    private UserDetailRepository userDetailRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public RegistrationResponseDTO registerUser(String userName, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserRole userRole = roleRepository.findByAuthority("USER").get();

        Set<UserRole> authorities = new HashSet<>();
        authorities.add(userRole);

        UserApplication registeredUser = userRepository.save(new UserApplication(0, email, encodedPassword, authorities));

        UserApplicationDetails userApplicationDetails = new UserApplicationDetails(userName);
        userApplicationDetails.setUserApplication(registeredUser);
        userDetailRepository.save(userApplicationDetails);

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

        String idToken = tokenService.generateJwt(auth);
        String refreshToken = tokenService.generateRefreshToken();

        RegistrationResponseDTO responseDTO = new RegistrationResponseDTO();
        responseDTO.setUserId(registeredUser.getUserId());
        responseDTO.setIdToken(idToken);
        responseDTO.setRefreshToken(refreshToken);

        return responseDTO;
    }


    public LoginResponseDTO loginUser(String email, String password) {
        Authentication auth = authenticateUser(email, password);
        UserApplication loginUser = getUserByEmail(email);

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setUserId(loginUser.getUserId());
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

    private UserApplication getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationError("User not found for email: " + email));
    }

    public boolean deleteUser(Integer userId){
            Optional<UserApplication> userByUserId = userRepository.findByUserId(userId);
            if (userByUserId.isPresent()) {
                userRepository.delete(userByUserId.get());
                return true;
            }
            return false;
        }
}
