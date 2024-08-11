package com.elearn.app.elearn_bak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.elearn.app.elearn_bak.dtos.CustomMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessage> handleResourceNotFound(ResourceNotFoundException exception){

        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(exception.getMessage());
        customMessage.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage); 

    }
}
