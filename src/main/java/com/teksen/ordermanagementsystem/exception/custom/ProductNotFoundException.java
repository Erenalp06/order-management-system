package com.teksen.ordermanagementsystem.exception.custom;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
