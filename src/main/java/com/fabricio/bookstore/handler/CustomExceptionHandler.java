package com.fabricio.bookstore.handler;

import com.fabricio.bookstore.exceptions.DataIntegrityViolationException;
import com.fabricio.bookstore.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> resourceNotFoundException(ResourceNotFoundException e, ServletRequest request) {
        Error error = new Error(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request) {
        Error error = new Error(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(com.fabricio.bookstore.exceptions.MethodArgumentNotValidException.class)
    public ResponseEntity<Error> methodArgumentNotValidException(com.fabricio.bookstore.exceptions.MethodArgumentNotValidException e, ServletRequest request) {
        Error error = new Error(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Error> validationError(org.springframework.web.bind.MethodArgumentNotValidException e, ServletRequest request) {
        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.addError(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
