package com.example.gcccyclingapp;

//package com.example.myapplication;

public class Account {

    String username, password, role;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean validLogin(String inputUsername, String inputPassword) {
        // is case sensitive
        if (!this.username.equals(inputUsername) || !this.password.equals(inputPassword)) {
            return false;
        }
        return true;
    }

}