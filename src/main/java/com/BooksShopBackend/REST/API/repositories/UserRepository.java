package com.BooksShopBackend.REST.API.repositories;

import com.BooksShopBackend.REST.API.models.UserApplication;
import com.BooksShopBackend.REST.API.models.UserApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApplication, Integer> {
    Optional<UserApplication> findByUserId(Integer userId);
    Optional<UserApplication> findByEmail(String email);
}
