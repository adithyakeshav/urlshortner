package com.keshava.shorten.exceptionhandler;

public class UrlAlreadyTakenException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UrlAlreadyTakenException() {
    }

    public UrlAlreadyTakenException(String message) {
        super(message);
    }

    public UrlAlreadyTakenException(Throwable cause) {
        super(cause);
    }

    public UrlAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlAlreadyTakenException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}