package com.keshava.shorten.exceptionhandler;

public class ResourceNotFoundException extends IllegalArgumentException {

    /**
     *
     */
    private static final long serialVersionUID = 8221675519767188106L;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String s) {
        super(s);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}