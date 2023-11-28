package com.example.gcccyclingapp;

public class Event {

    String name, type, difficulty, fee, route, limit, date;

    public Event (String name, String type, String difficulty, String fee, String route, String limit, String date) {
        this.name = name;
        this.type = type;
        this. difficulty = difficulty;
        this.fee = fee;
        this.route = route;
        this.limit = limit;
        this.date = date;
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
        this.type= type;
    }

//    public String getDifficulty() {
//        return this.difficulty;
//    }
//    public void setDifficulty(String difficulty) {
//        this.difficulty= difficulty;
//    }
//
//    public String getFee() {
//        return this.fee;
//    }
//    public void setFee(String fee) {
//        this.fee= fee;
//    }
//
//    public String getRoute() {
//        return this.route;
//    }
//    public void setRoute(String route) {
//        this.route= route;
//    }
//
//    public String getLimit() {
//        return this.limit;
//    }
//    public void setLimit(String limit) {
//        this.limit= limit;
//    }
//
//    public String getDate() {
//        return this.date;
//    }
//    public void setDate(String date) {
//        this.date= date;
//    }

}
