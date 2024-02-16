package com.teksen.ordermanagementsystem.exception;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class ExceptionResponse {
    private String message;
    private String description;
    private HttpStatus httpStatus;
    private Timestamp timestamp;


    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String description, HttpStatus httpStatus, Timestamp timestamp) {
        this.message = message;
        this.description = description;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
