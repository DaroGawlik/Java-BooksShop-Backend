package com.BooksShopBackend.REST.API.repositories.orders;


import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserUserId(Integer userId);
}
