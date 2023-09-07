package com.BooksShopBackend.REST.API.Services;

import com.BooksShopBackend.REST.API.models.Errors.UserNotFoundError;
import com.BooksShopBackend.REST.API.models.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.account.UserDataDetailResponseDTO;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

    @Autowired
    private  UserDetailRepository userDetailRepository;

    public UserDataDetailResponseDTO getUserDataDetail(Integer userId) {
        UserApplicationDetails userApplicationDetails = getUserById(userId);

        UserDataDetailResponseDTO responseDTO = new UserDataDetailResponseDTO();
        responseDTO.setUsername(userApplicationDetails.getUsername());
        return responseDTO;
    }

    private UserApplicationDetails getUserById(Integer userId) {
        return userDetailRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundError("User not found for ID: " + userId));
    }
}
