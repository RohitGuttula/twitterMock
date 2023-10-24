package com.example.twitter.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString


public class ErrorResponse {

    private final String errorCode;
    private final String errorMessage;


}

