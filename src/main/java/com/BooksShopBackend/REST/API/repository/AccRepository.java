package com.BooksShopBackend.REST.API.repository;

import com.BooksShopBackend.REST.API.domain.User;

import java.util.Collection;

public interface AccRepository <T extends Acc> {
    /* Basic CRUD Operations */
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* More Complex Operations */


    void addAccToUser(Long userId, String accName)
}
