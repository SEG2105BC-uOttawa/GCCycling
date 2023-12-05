package com.example.gcccyclingapp;

import android.os.Bundle;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class Participant extends Account {

    protected String name;
    private DBAdmin DBa;
    private DBClubs DBc;

    public Participant(String name, String username, String password, String role, Context context) {
        super(username, password, role);
        this.name = name;

        this.DBa = new DBAdmin(context);
        this.DBc = new DBClubs(context);

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String[] eventDiscoveryByCriteria(String criteria, String criteriaValue) {
        String[] events;
        switch(criteria.toLowerCase()) {
            case "location":
                System.out.println("Criteria: " + criteria);
                System.out.println("Location: "+criteriaValue);
                String[] eventTypes = DBa.getAllEventsByLocation(criteriaValue);
                return DBc.getAllEventsByLocation(eventTypes, DBa);
            case "date":
                System.out.println("Criteria: " + criteria);
                System.out.println("Date: "+criteriaValue);
                return DBc.getAllEventsByDate(criteriaValue, DBa);
            case "type":
                System.out.println("Criteria: " + criteria);
                System.out.println("Type: "+criteriaValue);
                return DBc.getAllEventsByType(criteriaValue, DBa);
        }
        return null; // if criteria is neither of the three options
    }

}
