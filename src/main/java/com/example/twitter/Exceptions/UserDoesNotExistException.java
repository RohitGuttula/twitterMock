package com.example.twitter.Exceptions;

import com.example.twitter.ExceptionHandler.HttpStatusCodes;

public class UserDoesNotExistException extends RuntimeException {
    private final HttpStatusCodes httpStatus;

    public UserDoesNotExistException(HttpStatusCodes httpStatus,String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatusCodes getHttpStatus() {
        return httpStatus;
    }
}
