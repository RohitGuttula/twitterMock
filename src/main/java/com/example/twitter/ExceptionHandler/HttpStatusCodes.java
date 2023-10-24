package com.example.twitter.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatusCodes {
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500),
    NOT_FOUND(404);

    private final int code;
}
