package com.project.simpleboard.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class AdviceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ExceptionResponse(e.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler({SecurityException.class, MalformedJwtException.class, ExpiredJwtException.class,
            UnsupportedJwtException.class, IllegalArgumentException.class, BadCredentialsException.class})
    public ExceptionResponse handleUnauthorized(Exception e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(UnauthorizedBehaviorException.class)
    public ExceptionResponse handleForbidden(UnauthorizedBehaviorException e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler({NoSuchElementException.class, UsernameNotFoundException.class})
    public ExceptionResponse handleNotFound(Exception e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleInternalServerError(Exception e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
