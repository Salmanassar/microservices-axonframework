package com.appsdeveloper.estore.productservice.core.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ProductsServiceExceptionHandling {
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> illegalStateExceptionHandler(IllegalStateException ex, WebRequest webRequest){
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest webRequest){
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
