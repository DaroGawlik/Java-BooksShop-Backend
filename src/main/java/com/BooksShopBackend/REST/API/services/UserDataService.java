package com.BooksShopBackend.REST.API.services;

import com.BooksShopBackend.REST.API.models.errors.UserNotFoundError;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.account.UserDataDetailResponseDTO;
import com.BooksShopBackend.REST.API.models.account.UserDataUpdateUserNameDTO;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    private UserApplicationDetails getUserById(Integer userId) {
        Optional<UserApplicationDetails> userByUserId = userDetailRepository.findByUserId(userId);
        if (userByUserId.isPresent()) {
            return userByUserId.get();
        } else {
            throw new UserNotFoundError("User with the provided ID was not found.");
        }
    }
    public UserDataDetailResponseDTO getUserDataDetail(Integer userId) {
        UserApplicationDetails userApplicationDetails = getUserById(userId);

        UserDataDetailResponseDTO responseDTO = new UserDataDetailResponseDTO();
        responseDTO.setUserName(userApplicationDetails.getUserName());
        return responseDTO;
    }

    public UserDataUpdateUserNameDTO updateUserName(Integer userId, String newUserName) {
        UserApplicationDetails userApplicationDetails = getUserById(userId);
        userApplicationDetails.setUserName(newUserName);
        userDetailRepository.save(userApplicationDetails);

        UserDataUpdateUserNameDTO responseDTO = new UserDataUpdateUserNameDTO();
        responseDTO.setUserName(userApplicationDetails.getUserName());
        return responseDTO;
    }
}

