package com.BooksShopBackend.REST.API.controllers.books;


import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.services.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BooksController {

    @Autowired
    BookService bookService;


    @GetMapping("/list")
    public Iterable<BooksList> list(){return bookService.list();}


}
