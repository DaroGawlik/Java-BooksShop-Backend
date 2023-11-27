package com.BooksShopBackend.REST.API.repositories.orders;

import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderDeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDeliveryAddressRepository extends JpaRepository<OrderDeliveryAddress, Integer>{
    OrderDeliveryAddress findByOrderId(Integer orderId);
}
