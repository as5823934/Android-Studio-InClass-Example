package com.example.huntertsai.firebasedemo;

/**
 * Created by huntertsai on 2018-02-24.
 */

public class User {
    String uudi, name, email;

    public User(){

    }

    public User(String uudi, String name, String email) {
        this.uudi = uudi;
        this.name = name;
        this.email = email;
    }

    public String getUudi() {
        return uudi;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUudi(String uudi) {
        this.uudi = uudi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
