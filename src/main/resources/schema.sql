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
    non_locked    BOOLEAN DEFAULT TRUE,
    created_date  DATETIME DEFAULT CURRENT_TIMESTAMP,
    enabled       BOOLEAN DEFAULT FALSE,
    CONSTRAINT    UQ_UsersAuth_Email UNIQUE (e_mail)
);