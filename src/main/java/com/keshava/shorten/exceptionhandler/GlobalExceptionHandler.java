package com.keshava.shorten.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGlobalException(Exception exception, HttpServletRequest request) {
        return new ErrorResponse(exception.getMessage(), request.getRequestURI());
    }
}