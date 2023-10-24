package com.example.twitter.ExceptionHandler;

import com.example.twitter.Exceptions.EmailAlreadyFoundException;
import com.example.twitter.Exceptions.UserDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyFoundException(EmailAlreadyFoundException ex){
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getHttpStatus().name());
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(httpStatus.value()), ex.getMessage());
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
    @ExceptionHandler(UserDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserDoesNotFoundException(UserDoesNotExistException ex){
        HttpStatus httpStatus=HttpStatus.valueOf(ex.getHttpStatus().name());
        ErrorResponse errorResponse=new ErrorResponse(String.valueOf(httpStatus.value()),ex.getMessage());
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
