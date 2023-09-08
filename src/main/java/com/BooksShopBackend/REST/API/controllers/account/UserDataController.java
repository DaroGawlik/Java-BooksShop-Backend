package com.BooksShopBackend.REST.API.controllers.account;

import com.BooksShopBackend.REST.API.Services.UserDataService;
import com.BooksShopBackend.REST.API.models.Errors.ApplicationError;
import com.BooksShopBackend.REST.API.models.Errors.InvalidCredentialsError;
import com.BooksShopBackend.REST.API.models.Errors.UserNotFoundError;
import com.BooksShopBackend.REST.API.models.account.UserDataDetailResponseDTO;
import com.BooksShopBackend.REST.API.models.account.UserDataUpdateUserNameDTO;
import com.BooksShopBackend.REST.API.repositories.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userData")
@CrossOrigin("*")
public class UserDataController {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/{userId}/get")
    public ResponseEntity<?> getUserDataDetail(@PathVariable Integer userId) {
        try {
            UserDataDetailResponseDTO responseDTO = userDataService.getUserDataDetail(userId);
            return ResponseEntity.ok(responseDTO);
        } catch (InvalidCredentialsError e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        } catch (UserNotFoundError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (ApplicationError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @PutMapping("/{userId}/updateUserName")
    public ResponseEntity<?> updateUserName(@PathVariable Integer userId, @RequestBody UserDataUpdateUserNameDTO body) {
        try {
            UserDataUpdateUserNameDTO responseDTO = userDataService.updateUserName(userId, body.getUserName());
            return ResponseEntity.ok(responseDTO);
        } catch (UserNotFoundError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        } catch (ApplicationError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}

