package com.teksen.ordermanagementsystem.exception.custom;


public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message) {
        super(message);
    }
}
