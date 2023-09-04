package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.models.ApplicationUser;
import com.BooksShopBackend.REST.API.models.LoginResponseDTO;
import com.BooksShopBackend.REST.API.models.RegistrationResponseDTO;
import com.BooksShopBackend.REST.API.models.Role;
import com.BooksShopBackend.REST.API.repository.RoleRepository;
import com.BooksShopBackend.REST.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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

    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
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


//    public LoginResponseDTO loginUser(String username, String password){
//
//        try{
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            String token = tokenService.generateJwt((auth));
//
//            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
//        } catch(AuthenticationException e){
//            return new LoginResponseDTO(null, "");
//        }
//    }

