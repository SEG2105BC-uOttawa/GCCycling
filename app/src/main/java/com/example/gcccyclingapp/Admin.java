package com.example.gcccyclingapp;

public class Admin {

    public String username = "admin";
    public String pwd = "admin";
    public String role = "Admin";

    public void createClub(DBAdmin dbAdmin, DBClubs dbClubs, Club club){
        dbAdmin.insert(club.getClubName(), club.username, club.password);
        dbClubs.createTable(club.getClubName());
    }

//    public void deleteClub(DBAdmin dbAdmin, DBClubs dbClubs, Club club){
//        dbClubs.deleteTable(club.getClubName());
//        dbAdmin.delete(club.getClubName());
//    }

    public void addParticipant(DBClubs dbClubs, Club club, Participant participant){
        dbClubs.addParticipant(club.getClubName(), participant.name, participant.username, participant.password); // delete participant from a club table
    }
//    public void deleteParticipant(DBClubs dbClubs, Club club, Participant participant){
//        dbClubs.deleteParticipant(club.getClubName(), participant.name); // delete participant from a club table
//    }



}
