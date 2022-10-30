package com.keshava.shorten.entity;

public class ShortenRequest {
    private String shortString;
    private String expansionString;

    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }

    public ShortenRequest(String shortString, String expansionString) {
        this.shortString = shortString;
        this.expansionString = expansionString;
    }

    public ShortenRequest() {
    }

    public String getExpansionString() {
        return expansionString;
    }

    public void setExpansionString(String expansionString) {
        this.expansionString = expansionString;
    }
}
