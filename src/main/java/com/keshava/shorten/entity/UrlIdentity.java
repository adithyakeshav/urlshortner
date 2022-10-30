package com.keshava.shorten.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UrlIdentity implements Serializable {
    private String username;
    private String shortString;

    @Override
    public String toString() {
        return " {" +
                "user='" + username + '\'' +
                ", shortString='" + shortString + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public UrlIdentity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortString() {
        return shortString;
    }

    public void setShortString(String shortString) {
        this.shortString = shortString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlIdentity that = (UrlIdentity) o;
        return Objects.equals(username, that.username) && Objects.equals(shortString, that.shortString);
    }

    public UrlIdentity(String username, String shortString) {
        this.username = username;
        this.shortString = shortString;
    }
}