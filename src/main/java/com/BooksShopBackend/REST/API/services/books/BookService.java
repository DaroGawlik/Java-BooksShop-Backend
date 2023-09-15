package com.BooksShopBackend.REST.API.services.books;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.repositories.BookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookListRepository bookListRepository;

    public Iterable<BooksList> list(){
        return bookListRepository.findAll();
    }

    public BooksList save(BooksList booksList){
        return bookListRepository.save((booksList));
    }

    public void save(List<BooksList> booksLists){
        bookListRepository.saveAll(booksLists);
    }


}
