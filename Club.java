package com.example.gcccyclingapp;

public class Club extends Account {

    private String clubName;

    public Club(String clubName, String username, String password, String role) {
        super(username, password, role);
        this.clubName = clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return this.clubName;
    }

}