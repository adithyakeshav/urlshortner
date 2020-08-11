package com.keshava.shorten.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHORTENER")
public class UrlShortener {
    
    @Id
    @Column(name = "short")
    private String shortString ;

    @Column(name = "expansion")
    private String expasionString;

    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }

    public String getExpasionString() {
        return expasionString;
    }

    public void setExpasionString(String expasionString) {
        this.expasionString = expasionString;
    }

    
}