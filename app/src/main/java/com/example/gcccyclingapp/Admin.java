package com.example.gcccyclingapp;

import android.content.Context;

public class Admin extends Account {
    DBAdmin dbAdmin;
    DBClubs dbClubs;

    public Admin(Context context) {
        super("admin", "admin", "Admin");
        dbAdmin = new DBAdmin(context);
        dbClubs = new DBClubs(context);
    }

    public void createClub(Club club){
        dbAdmin.insert(club.getClubName(), club.username, club.password);
        dbClubs.createTable(club.getClubName());
    }

//    public void deleteClub(DBAdmin dbAdmin, DBClubs dbClubs, Club club){
//        dbClubs.deleteTable(club.getClubName());
//        dbAdmin.delete(club.getClubName());
//    }

    public void addParticipant(Club club, Participant participant){
        dbClubs.addParticipant(club.getClubName(), participant.name, participant.username, participant.password); // delete participant from a club table
    }
//    public void deleteParticipant(DBClubs dbClubs, Club club, Participant participant){
//        dbClubs.deleteParticipant(club.getClubName(), participant.name); // delete participant from a club table
//    }



}
