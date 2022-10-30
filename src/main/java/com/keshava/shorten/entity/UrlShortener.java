package com.keshava.shorten.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shortener")
public class UrlShortener {

    @EmbeddedId
    private UrlIdentity id;

    @Column(name = "expansion")
    private String expansionString;

    public UrlIdentity getId() {
        return id;
    }

    public UrlShortener(UrlIdentity id, String expansionString) {
        this.id = id;
        this.expansionString = expansionString;
    }

    public void setId(UrlIdentity id) {
        this.id = id;
    }

    public String getExpansionString() {
        return expansionString;
    }

    public void setExpansionString(String expasionString) {
        this.expansionString = expasionString;
    }

    public UrlShortener() {
    }
}