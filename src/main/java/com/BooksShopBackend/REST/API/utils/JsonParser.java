package com.BooksShopBackend.REST.API.utils;

import com.BooksShopBackend.REST.API.models.dataBase.Books;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonParser {

    ObjectMapper objectMapper = new ObjectMapper();

    public List<Books> parseJsonToBooks(String jsonFilePath) throws IOException {
        File file = new File(jsonFilePath);
        return objectMapper.readValue(file, new TypeReference<List<Books>>(){});
    }
}
