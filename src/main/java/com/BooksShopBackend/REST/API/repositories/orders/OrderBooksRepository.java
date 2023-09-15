package com.BooksShopBackend.REST.API.repositories.orders;

import com.BooksShopBackend.REST.API.models.dataBase.order.OrderBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBooksRepository extends JpaRepository<OrderBooks, Integer> {

}