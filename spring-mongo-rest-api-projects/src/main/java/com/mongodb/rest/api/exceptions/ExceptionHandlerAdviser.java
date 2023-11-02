package com.mongodb.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdviser {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return new ResponseEntity<>(Map.of("status", "Data Not Found", "statusCode", 404, "message", ex.getMessage(), "isSuccess", true), HttpStatus.NOT_FOUND);
    }
}
