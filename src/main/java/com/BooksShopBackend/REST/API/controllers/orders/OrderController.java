package com.BooksShopBackend.REST.API.controllers.orders;


import com.BooksShopBackend.REST.API.models.errors.ApplicationError;
import com.BooksShopBackend.REST.API.models.errors.EmailAlreadyExistsExceptionError;
import com.BooksShopBackend.REST.API.models.ordersDTO.OrderGetResponseDTO;
import com.BooksShopBackend.REST.API.models.ordersDTO.OrderPostDTO;
import com.BooksShopBackend.REST.API.services.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/order/{userId}")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/post")
    public ResponseEntity<?> OrderPost(@PathVariable Integer userId, @RequestBody OrderPostDTO body) throws ParseException {
        System.out.println("Received request body: " + body);
        String orderResponse  = orderService.OrderPost(userId, body);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> OrderGet(@PathVariable Integer userId, @RequestParam(name = "returnSecureToken", defaultValue = "true") boolean returnSecureToken
    ) throws ParseException {
        if (!returnSecureToken) {
            ApplicationError e = new ApplicationError("Get orders with returnSecureToken = false is not allowed");
            return ResponseEntity.badRequest().body(e);
        }
        try {
            List<OrderGetResponseDTO> responseDTOList = orderService.OrderGet(userId);
            return ResponseEntity.ok(responseDTOList);
        } catch (EmailAlreadyExistsExceptionError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
