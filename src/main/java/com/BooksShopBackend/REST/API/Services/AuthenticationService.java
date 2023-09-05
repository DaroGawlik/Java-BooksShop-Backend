package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.models.*;
import com.BooksShopBackend.REST.API.repository.RoleRepository;
import com.BooksShopBackend.REST.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
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

        Optional<ApplicationUser> registerUserOptional = userRepository.findByEmail(email);

        if(!registerUserOptional.isPresent()){
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
        } else {
            throw new ApplicationError("This" + email + " is registred already");
        }
    }

    public LoginResponseDTO loginUser(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            Optional<ApplicationUser> loginUserOptional = userRepository.findByEmail(email);

            if (loginUserOptional.isPresent()) {
                ApplicationUser loginUser = loginUserOptional.get();

                // Porównaj zaszyfrowane hasło z hasłem w bazie danych
                if (passwordEncoder.matches(password, loginUser.getPassword())) {
                    // Hasło jest poprawne, możesz utworzyć odpowiedź
                    LoginResponseDTO responseDTO = new LoginResponseDTO();
                    String idToken = tokenService.generateJwt(auth);
                    String refreshToken = tokenService.generateRefreshToken();

                    responseDTO.setUserId(loginUser.getUserId().toString());
                    responseDTO.setUsername(loginUser.getUsername());
                    responseDTO.setIdToken(idToken);
                    responseDTO.setRefreshToken(refreshToken);

                    return responseDTO;
                } else {
                    throw new ApplicationError("Login failed due to invalid credentials");
                }
            } else {
                throw new ApplicationError("User not found for email: " + email);
            }
        } catch (AuthenticationException e) {
            throw new ApplicationError("Login failed due to invalid credentials");
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

