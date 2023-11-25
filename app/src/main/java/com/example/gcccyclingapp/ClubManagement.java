package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class ClubManagement extends AppCompatActivity{

    private String eventType, eventDifficulty, eventRoute, eventFee, eventParticipantLimit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);
    }

    // the created event will automatically be added to the club's database of events
    public boolean createEvent(String clubName) {
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

        clubDB.insertEvent(clubName, eventType, eventDifficulty, eventRoute, eventFee, eventParticipantLimit);

        return true;
    }

    public void editEvent (String clubName, String eventName) {
        DBClubs clubDB = new DBClubs(this);

        // club owner can't change event details set by admin
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
        clubDB.insertEvent(clubName, eventType, eventDifficulty, eventRoute, eventFee, eventParticipantLimit);
    }

    public void deleteEvent(String clubName, String event) {
        DBClubs clubDB = new DBClubs(this);
        clubDB.deleteEvent(clubName, event);
    }
}
