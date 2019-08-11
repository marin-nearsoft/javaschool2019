package com.example.demo.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleError(){
        return new ResponseEntity<>("My F message 2", HttpStatus.NOT_ACCEPTABLE);
    }

}
