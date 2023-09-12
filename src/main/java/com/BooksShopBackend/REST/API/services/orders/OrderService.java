package com.BooksShopBackend.REST.API.services.orders;


import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplicationDetails;
import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
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


    public String OrderPost(Integer userId,OrderPostDTO body){
        Order order = new Order();
        UserApplication user = new UserApplication();
        user.setUserId(userId);
        order.setUser(user);
        orderRepository.save(order);

        return "ok";
    }
}