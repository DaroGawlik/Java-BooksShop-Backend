CREATE SCHEMA IF NOT EXISTS BooksShop;

SET NAMES 'utf8mb4';
SET TIME_ZONE = 'Europe/Warsaw';

USE BooksShop;

DROP TABLE IF EXISTS UsersAuth;

CREATE TABLE UsersAuth
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    e_mail        VARCHAR(100) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    CONSTRAINT    UQ_UsersAuth_Email UNIQUE (e_mail)
);


DROP TABLE IF EXISTS booksLists;

CREATE TABLE booksLists (
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    author VARCHAR(255),
    price DECIMAL(10, 2),
    title VARCHAR(255)
);


CREATE TABLE OrderBooks (
    order_id INT,
    book_id INT,
    amount INT,
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (book_id) REFERENCES Books(id)
);