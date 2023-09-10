package com.BooksShopBackend.REST.API.services.orders;


import com.BooksShopBackend.REST.API.models.orders.OrderPostDTO;
import com.BooksShopBackend.REST.API.repositories.orders.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    private  OrderPost(OrderPostDTO body){
return "ok";
    }
}
