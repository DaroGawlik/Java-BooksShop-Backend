package com.BooksShopBackend.REST.API.repositories.orders;

import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderBooksRepository extends JpaRepository<OrderBooks, Long> {
    List<OrderBooks> findByOrder(Order order);
}