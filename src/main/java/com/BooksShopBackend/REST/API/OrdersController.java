package com.BooksShopBackend.REST.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @GetMapping("/user")
    public String getUser(){
        return "darek";
    }
}