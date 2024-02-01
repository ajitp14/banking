package com.myspace.banking.application.exception;

import com.myspace.banking.application.model.Error;
import com.myspace.banking.application.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoFundsException.class)
    public ResponseEntity<ErrorResponse> handleNoFundsException(NoFundsException ex){
        System.out.println("Inside funds exception handler");
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setCode(ex.getErrorCode());
        error.setMessage(ex.getErrorMessage());
        error.setDetails(ex.getErrorDetails());
        List<Error> errors = List.of(error);
        errorResponse.setError(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoElementException(NoSuchElementException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setCode("1002");
        error.setMessage("Not Found");
        error.setDetails("Requested Account/Customer Not Found");
        List<Error> errors = List.of(error);
        errorResponse.setError(errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
