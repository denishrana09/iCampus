package com.example.denish.icampus.Model;

/**
 * Created by denish on 7/4/18.
 */

public class user {
    String name;
    String grant;

    public user(String name, String grant) {
        this.name = name;
        this.grant = grant;
    }

    public user() {
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
}
