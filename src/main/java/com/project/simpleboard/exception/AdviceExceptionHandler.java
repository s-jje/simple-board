package com.project.simpleboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ExceptionResponse handleException(Exception e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ExceptionResponse(e.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
