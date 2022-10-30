package com.keshava.shorten.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "passwd")
    private String password ;

    @Column(name = "roles")
    private String roles;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity() {
    }
}
