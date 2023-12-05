package com.BooksShopBackend.REST.API.controllers.orders;


import com.BooksShopBackend.REST.API.models.errors.*;
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
    public ResponseEntity<?> OrderPost(@PathVariable int userId, @RequestBody OrderPostDTO body) throws ParseException {
        try {
            String orderResponse  = orderService.OrderPost(userId, body);
            return ResponseEntity.ok(orderResponse);
        } catch (UserNotFoundError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/get")
    public ResponseEntity<?> OrderGet(@PathVariable int userId, @RequestParam(name = "returnSecureToken", defaultValue = "true") boolean returnSecureToken
    ) throws ParseException {
        if (!returnSecureToken) {
            TokenValueError e = new TokenValueError("Something is wrong with your token: registration isn't possible");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        try {
            List<OrderGetResponseDTO> responseDTOList = orderService.OrderGet(userId);
            return ResponseEntity.ok(responseDTOList);
        } catch (UserNotFoundError | OrderNotFoundError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") int orderId, @RequestParam(name = "returnSecureToken", defaultValue = "true") boolean returnSecureToken
    ) throws ParseException {
        if (!returnSecureToken) {
            TokenValueError e = new TokenValueError("Something is wrong with your token: registration isn't possible");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        try {
            String response = orderService.DeleteOrder(orderId);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
