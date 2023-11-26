package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class ClubManagement extends AppCompatActivity{

    private String eventType, eventName, eventDifficulty, eventRoute, eventFee, eventParticipantLimit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_type);
    }

    // the created event will automatically be added to the club's database of events
    public boolean createEvent(String clubName) {
        DBClubs clubDB = new DBClubs(this);

        EditText name = (EditText) findViewById(R.id.eventTypetxt);
        EditText type = (EditText) findViewById(R.id.eventTypetxt);
        EditText difficulty = (EditText) findViewById(R.id.eventTypetxt);
        EditText route = (EditText) findViewById(R.id.eventTypetxt);
        EditText fee = (EditText) findViewById(R.id.eventTypetxt);
        EditText participantLimit = (EditText) findViewById(R.id.eventTypetxt);

        eventName =  type.getText().toString();
        eventType =  type.getText().toString();
        eventDifficulty = difficulty.getText().toString();
        eventRoute = route.getText().toString();
        eventFee = fee.getText().toString();
        eventParticipantLimit = participantLimit.getText().toString();

        Boolean check = true;
        String warningText = "";

        if (eventName.equals("")) {
            warningText += "Name is invalid. ";
            check = false;
        }

        if (clubDB.findEvent(clubName, eventName)) {
            warningText += "Event already exists. ";
            check = false;
        }

        if (eventType.equals("")) {
            warningText += "Type is invalid. ";
            check = false;
        }
        if (eventDifficulty.equals("")) {
            warningText += "Difficulty is invalid. ";
            check = false;
        }
        if (eventRoute.equals("")) {
            warningText += "Route is invalid. ";
            check = false;
        }
        if (eventFee.equals("")) {
            warningText += "Fee is invalid. ";
            check = false;
        }
        if (eventParticipantLimit.equals("")) {
            warningText += "Participant Limit is invalid. ";
            check = false;
        }

        TextView warning = (TextView) findViewById(R.id.warningtxt);
        warning.setText(warningText);

        clubDB.insertEvent(clubName, eventName, eventType, eventDifficulty, eventRoute, eventFee, eventParticipantLimit);

        return true;
    }

    // club owner can't change event details set by admin
    public void editEvent (String clubName, String eventName) {
        DBClubs clubDB = new DBClubs(this);

        EditText type = (EditText) findViewById(R.id.eventTypetxt);
        EditText difficulty = (EditText) findViewById(R.id.eventTypetxt);
        EditText route = (EditText) findViewById(R.id.eventTypetxt);
        EditText fee = (EditText) findViewById(R.id.eventTypetxt);
        EditText participantLimit = (EditText) findViewById(R.id.eventTypetxt);

        eventType =  type.getText().toString();
        eventDifficulty = difficulty.getText().toString();
        eventRoute = route.getText().toString();
        eventFee = fee.getText().toString();
        eventParticipantLimit = participantLimit.getText().toString();

        // cant perform the edit by deleting and reinserting into the database
        clubDB.deleteEvent(clubName, eventName);
        clubDB.insertEvent(clubName, eventName, eventType, eventDifficulty, eventRoute, eventFee, eventParticipantLimit);
    }

    public void deleteEvent(String clubName, String event) {
        DBClubs clubDB = new DBClubs(this);
        clubDB.deleteEvent(clubName, event);
    }

}
