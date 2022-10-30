package com.keshava.shorten.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UrlIdentity implements Serializable {
    private String user;
    private String shortString;

    @Override
    public String toString() {
        return " {" +
                "user='" + user + '\'' +
                ", shortString='" + shortString + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public UrlIdentity() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
        return Objects.equals(user, that.user) && Objects.equals(shortString, that.shortString);
    }

    public UrlIdentity(String user, String shortString) {
        this.user = user;
        this.shortString = shortString;
    }
}