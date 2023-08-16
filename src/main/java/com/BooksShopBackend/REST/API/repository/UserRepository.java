package com.BooksShopBackend.REST.API.repository;

import java.util.Collection;


import com.BooksShopBackend.REST.API.domain.User;

public interface UserRepository<T extends User> {
    /* Basic CRUD Operations */
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* More Complex Operations */
}