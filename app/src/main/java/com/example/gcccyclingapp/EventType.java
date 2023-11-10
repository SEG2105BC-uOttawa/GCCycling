package com.example.gcccyclingapp;

public class EventType {

    private String name; // event type name
    private String age;
    private String pace;
    private String level;
    private String location;
    private String time;
    private String details;


    public EventType(String name, String age, String pace, String level, String location, String time, String details){
        this.name = name;
        this.age = age;
        this.pace = pace;
        this.level = level;
        this.location = location;
        this.time = time;
        this.details = details;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPace(String pace) {
        this.pace = pace;
    }
    public String getPace() {
        return pace;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getLevel() {
        return level;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return details;
    }
}
