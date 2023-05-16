package com.mx.conectasalud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mx.conectasalud.exception.ErrorRestClinica;
import com.mx.conectasalud.exception.RequestException;

@RestControllerAdvice
public class ControllerAdvice {
    
    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorRestClinica> requestExceptionHandler(RequestException ex){
        ErrorRestClinica error = ErrorRestClinica.builder().title(ex.getTitle()).severity(ex.getSeverity()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    } 
}
