package com.example.restapiapplication.restAPI.exceptions;

import org.springframework.http.HttpStatus;

public class CustomInternException extends Exception {
    private final HttpStatus httpStatus;

    public CustomInternException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
