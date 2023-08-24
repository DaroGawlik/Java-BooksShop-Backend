package com.BooksShopBackend.REST.API.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {

    public static KeyPair generateRsaKey(){

        KeyPair keyPair;

        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e){
            throw new RuntimeException("Error generating RSA key pair", e);
        }
        return keyPair;
    }
}
