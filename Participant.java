package com.example.gcccyclingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Participant extends Account {

    protected String name;

    public Participant(String name, String username, String password, String role) {
        super(username, password, role);
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
