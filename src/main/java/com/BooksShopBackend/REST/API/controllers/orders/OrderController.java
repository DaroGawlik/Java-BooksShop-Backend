package com.BooksShopBackend.REST.API.controllers.orders;


import com.BooksShopBackend.REST.API.models.orders.OrderPostDTO;
import com.BooksShopBackend.REST.API.services.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/{userId}")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService


    @PostMapping("/post")
    public ResponseEntity<?> OrderPost(@PathVariable Integer userId, @RequestBody OrderPostDTO body){
        System.out.println("Received request body: " + body);

        String orderReposne  = orderService.OrderPost(body);
        return ResponseEntity.ok(orderReposne);
    }
}
