package com.example.gcccyclingapp;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class Admin extends Account {
    DBAdmin dbAdmin;
    DBClubs dbClubs;

    public Admin(Context context) {
        super("admin", "admin", "Admin");
        dbAdmin = new DBAdmin(context);
        dbClubs = new DBClubs(context);
    }

    public void createClub(Club club){
        dbAdmin.insertClub(club.getClubName(), club.username, club.password);
        dbClubs.addClub(club.getClubName());
    }
    public void createParticipant(Participant participant){
        dbAdmin.insertParticipant(participant.name, participant.username, participant.password);
//        dbClubs.createTable(club.getClubName());
    }

    public void deleteClub(DBAdmin dbAdmin, DBClubs dbClubs, Club club){
        dbClubs.deleteClub(club.getClubName());
        dbAdmin.deleteClub(club.getClubName());
    }

    public Map<String, Event[]> getAllClubs_Events() {

        Map<String, Event[]> clubs_events = new HashMap<>();
        String[] clubs = dbAdmin.getAllClubs();

        for (String club : clubs) {
            Event[] events = dbClubs.getAllEvents(club);
            clubs_events.put(club, events);
        }
        return clubs_events;
    }


//    public void addParticipant(Club club, Participant participant){ // add a participant to a club, will have to change for future use
//        dbClubs.addParticipant(club.getClubName(), participant.name, participant.username, participant.password); // delete participant from a club table
//    }
//    public void deleteParticipant(DBClubs dbClubs, Club club, Participant participant){
//        dbClubs.deleteParticipant(club.getClubName(), participant.name); // delete participant from a club table
//    }



}
