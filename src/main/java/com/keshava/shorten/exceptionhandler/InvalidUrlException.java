package com.keshava.shorten.exceptionhandler;

import java.net.MalformedURLException;

public class InvalidUrlException extends MalformedURLException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidUrlException() {
    }

    public InvalidUrlException(String msg) {
        super(msg);
    }

}