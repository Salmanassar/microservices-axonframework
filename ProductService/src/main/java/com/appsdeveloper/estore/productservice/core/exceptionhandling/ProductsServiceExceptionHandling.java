package com.appsdeveloper.estore.productservice.core.exceptionhandling;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ProductsServiceExceptionHandling {
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> illegalStateExceptionHandler(IllegalStateException ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<Object> commandExecutionExceptionHandler(CommandExecutionException ex,
                                                                   WebRequest webRequest) {
        return new ResponseEntity<>(getExceptionMessage(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ExceptionMessage getExceptionMessage(Exception ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), ex.getMessage());
        return exceptionMessage;
    }
}
