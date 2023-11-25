package com.example.gcccyclingapp;

public class EventType {
    String name, type, location,time, details;
    int age, level;

    Double pace;
    public EventType(String name, String type, int age, Double pace, int level, String location, String time, String details ) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.pace = pace;
        this.level = level;
        this.location = location;
        this.time = time;
        this.details = details;
    }

    public EventType() {

    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name= name;
    }

    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.name= type;
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age= age;
    }

    public Double getPace() {
        return this.pace;
    }
    public void setPace(Double pace) {
        this.pace = pace;
    }

    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level= level;
    }

    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location= location;
    }

    public String getTime() {
        return this.name;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return this.name;
    }
    public void setDetails(String details) {
        this.details = details;
    }


}
