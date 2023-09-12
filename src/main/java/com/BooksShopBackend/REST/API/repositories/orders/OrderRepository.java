package com.BooksShopBackend.REST.API.repositories.orders;


import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    Optional<Order> findByOrderId(Integer orderId);
}