package com.movies.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionAdviser {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatusCode(200);
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setTimestamp(new Date());
        errorMessage.setDescription("Resource not found");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
