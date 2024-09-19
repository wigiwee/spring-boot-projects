package com.elearn.app.elearn_bak.exception;

import com.elearn.app.elearn_bak.dtos.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessage> handleResourceNotFound(ResourceNotFoundException exception) {

        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(exception.getMessage());
        customMessage.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);

    }

    //handling dto object not valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();

// (FieldError)error:
// This part casts the error object from ObjectError to FieldError.
// The cast is necessary because ObjectError is a general type that could represent different kinds of errors, 
// but in this context, we specifically need to work with FieldError, which represents an error on a specific field of an object.

            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<CustomMessage> handleAuthDeniedException(AuthorizationDeniedException exception) {

        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(exception.getMessage());
        customMessage.setSuccess(false);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customMessage);

    }
}
