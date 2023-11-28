package com.BooksShopBackend.REST.API.repositories.orders;

import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderGifts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderGiftsRepository extends JpaRepository<OrderGifts, Integer> {
    OrderGifts findByOrderId(Integer orderId);
}
