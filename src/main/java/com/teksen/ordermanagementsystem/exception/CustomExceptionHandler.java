package com.teksen.ordermanagementsystem.exception;

import com.teksen.ordermanagementsystem.exception.custom.CustomerNotFoundException;
import com.teksen.ordermanagementsystem.exception.custom.OrderItemNotFoundException;
import com.teksen.ordermanagementsystem.exception.custom.OrderNotFoundException;
import com.teksen.ordermanagementsystem.exception.custom.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCustomerNotFoundException(
            CustomerNotFoundException customerNotFoundException,
            WebRequest request
    ){
        return getResponse(customerNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleOrderNotFoundException(
            OrderNotFoundException orderNotFoundException,
            WebRequest request
    ){
        return getResponse(orderNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleOrderItemNotFound(
            OrderItemNotFoundException orderItemNotFoundException,
            WebRequest request
    ){
        return getResponse(orderItemNotFoundException, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleProductNotFoundException(
            ProductNotFoundException productNotFoundException,
            WebRequest request
    ){
        return getResponse(productNotFoundException, HttpStatus.NOT_FOUND,request);
    }

    public ResponseEntity<Object> getResponse(
            RuntimeException e,
            HttpStatus httpStatus,
            WebRequest request
    ){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setTimestamp(new Timestamp(System.currentTimeMillis()));
        exceptionResponse.setDescription(request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }
}
