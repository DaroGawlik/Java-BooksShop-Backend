package com.BooksShopBackend.REST.API.repositories;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends JpaRepository<BooksList, Integer> {
    long count();

    BooksList findByTitle(String bookTitle);
}