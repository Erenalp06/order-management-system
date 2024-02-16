package com.teksen.ordermanagementsystem.exception.custom;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
