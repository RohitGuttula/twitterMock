package com.example.twitter.Exceptions;

import com.example.twitter.ExceptionHandler.HttpStatusCodes;

public class EmailAlreadyFoundException extends RuntimeException {
    private final HttpStatusCodes httpStatus;

    public EmailAlreadyFoundException(HttpStatusCodes httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatusCodes getHttpStatus() {
        return httpStatus;
    }
}
