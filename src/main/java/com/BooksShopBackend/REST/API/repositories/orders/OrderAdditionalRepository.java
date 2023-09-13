package com.BooksShopBackend.REST.API.repositories.orders;

import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderAdditional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderAdditionalRepository extends JpaRepository<OrderAdditional, Integer> {
    Optional<Order> findByOrderId(Integer orderId);
}