package com.example.gcccyclingapp;

public class Admin {

    public String username = "admin";
    public String pwd = "admin";
    public String role = "Admin";

    public void createClub(DBAdmin dbAdmin, DBClubs dbClubs, String clubName){
        dbAdmin.insert(clubName);
        dbClubs.createTable(clubName);
    }

    public void deleteClub(DBAdmin dbAdmin, DBClubs dbClubs, String clubName){ // fix delete
        dbAdmin.delete(clubName);
//        dbClubs.deleteTable(clubName);
    }



}
