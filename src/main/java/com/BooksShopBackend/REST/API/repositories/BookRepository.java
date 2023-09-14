package com.BooksShopBackend.REST.API.repositories;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BooksList, Integer> {
}
