package com.BooksShopBackend.REST.API.utils;

import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonParser {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<BooksList> parseJsonToBooks(String jsonFilePath) throws IOException {
        System.out.println("Parsing JSON file: " + jsonFilePath);
        File file = new File(jsonFilePath);
        List<BooksList> booksListList = objectMapper.readValue(file, new TypeReference<List<BooksList>>(){});
        System.out.println("Parsed " + booksListList.size() + " books from JSON.");
        return booksListList;
    }
}
