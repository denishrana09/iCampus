package com.example.denish.icampus.Model;

/**
 * Created by denish on 7/4/18.
 */

public class User {
    String name;
    String grant;
    String email;

    public User(String name, String grant, String email) {
        this.name = name;
        this.grant = grant;
        this.email = email;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrant() {
        return grant;
    }

    public void setGrant(String grant) {
        this.grant = grant;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", grant='" + grant + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
