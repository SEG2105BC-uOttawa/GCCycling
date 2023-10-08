package com.example.gcccyclingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Participant extends AppCompatActivity {

    protected String name;

    public Participant(String name) {
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
