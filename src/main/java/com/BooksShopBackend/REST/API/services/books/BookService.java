package com.BooksShopBackend.REST.API.services.books;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Iterable<BooksList> list(){
        return bookRepository.findAll();
    }

    public BooksList save(BooksList booksList){
        return bookRepository.save((booksList));
    }

    public void save(List<BooksList> booksLists){
        bookRepository.saveAll(booksLists);
    }
}
