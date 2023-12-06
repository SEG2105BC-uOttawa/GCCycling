package com.example.gcccyclingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParticipantBrowseEvents extends AppCompatActivity {

    private Event[] events;
    private String[] clubs;
    private List<String> participantsClubs;
    Map<String, Event[]> clubsEvents;
    private DBClubs dbc;
    private DBAdmin dba;
    Admin admin;
    ListView listView;
    Spinner filterSpinner;
    TextView noEventsMessage;
    String participant;
    List<Map<String, String>> listItems;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_events_screen);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("participant")!=null){
            participant = bundle.getString("participant");
        }


        dbc = new DBClubs(this);
        dba = new DBAdmin(this);
        admin = new Admin(this);
        clubsEvents = new HashMap<>();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.listView = (ListView) findViewById(R.id.browseEventsList);

        filterSpinner = (Spinner) findViewById(R.id.filter);
        displayFilterSpinner(filterSpinner);

        participantsClubs = dba.getParticipantClubs(participant); // array of all clubs the participant is part of

        clubs = dba.getAllClubs();

        for (String club : clubs) {
            events = dbc.getAllEventsObject(club); // get all event names in String[] format
            clubsEvents.put(club, events); // each key (club name) has an array of Events
        }

        listItems = new ArrayList<>();
        Map<String, Event[]> clubsEvents = admin.getAllClubs_Events();


        for (String club : clubsEvents.keySet()) {
            Event[] events = clubsEvents.get(club);
            for (Event event : events) {
                Log.d("event", event.getName());
                Log.d("club", club);
                Map<String, String> item = new HashMap<>();
                item.put("event", event.getName());

                if (!participantsClubs.isEmpty()) { // if the participant is part of clubs
                    if (participantsClubs.contains(club)) { // check if the club is one the participant is part of
                        item.put("club", "Club: "+ club + " - You are part of this club!");
                    } else {
                        item.put("club", "Club: "+ club);
                    }
                } else { // if the participant is not in any clubs, just format everything normally
                    item.put("club", "Club: "+ club);
                }
                listItems.add(item);
            }
        }

        noEventsMessage = (TextView) findViewById(R.id.noEventsMessage);

        if (events.length == 0) {
            noEventsMessage.setVisibility(View.VISIBLE); // if there are no events, show a message stating so
        } else {
            String[] from = {"event", "club"};
            int[] to = {R.id.text1, R.id.text2};

            filterSpinner.setVisibility((View.VISIBLE));
            listView.setVisibility(View.VISIBLE);
            SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.browse_events_list_item, from, to);
            listView.setAdapter(adapter);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String club = events[position].club;
                String event = events[position].name;

                showEditDeleteOptions(club, event);
            }
        });

    }
    private void displayFilterSpinner(Spinner spinner){

        String[] filters = {"Club", "Location", "Date", "Type"}; // options

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_layout, R.id.text1, filters);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedFilter = (String) parentView.getItemAtPosition(position); // get selected filter as a string

                List<Map<String, String>> newListItems = new ArrayList<>(); // new listItems to update listView
                Map<String, Event[]> clubsEvents = admin.getAllClubs_Events();

                switch(selectedFilter) {
                    case "Location":
                        for (String club : clubsEvents.keySet()) {
                            Event[] events = clubsEvents.get(club);
                            for (Event event : events) {
                                Log.d("event", event.getName());
                                Log.d("club", club);
                                Map<String, String> item = new HashMap<>();
                                item.put("event", event.getName());
                                item.put("location", "Location: "+dba.getEventLocation(event.type));

                                newListItems.add(item);
                            }
                        }
                        break;
                    case "Club":
                        for (String club : clubsEvents.keySet()) {
                            Event[] events = clubsEvents.get(club);
                            for (Event event : events) {
                                Log.d("event", event.getName());
                                Log.d("club", club);
                                Map<String, String> item = new HashMap<>();
                                item.put("event", event.getName());

                                if (!participantsClubs.isEmpty()) { // if the participant is part of clubs
                                    if (participantsClubs.contains(club)) { // check if the club is one the participant is part of
                                        item.put("club", "Club: "+ club + " - You are part of this club!");
                                    } else {
                                        item.put("club", "Club: "+ club);
                                    }
                                } else { // if the participant is not in any clubs, just format everything normally
                                    item.put("club", "Club: "+ club);
                                }
                                newListItems.add(item);
                            }
                        }
                        break;
                    case "Date":
                        for (String club : clubsEvents.keySet()) {
                            Event[] events = clubsEvents.get(club);
                            for (Event event : events) {
                                Log.d("event", event.getName());
                                Log.d("club", club);
                                Map<String, String> item = new HashMap<>();
                                item.put("event", event.getName());
                                item.put("date", "Date: "+ event.date);

                                newListItems.add(item);
                            }
                        }
                        break;
                    case "Type":
                        for (String club : clubsEvents.keySet()) {
                            Event[] events = clubsEvents.get(club);
                            for (Event event : events) {
                                Log.d("event", event.getName());
                                Log.d("club", club);
                                Map<String, String> item = new HashMap<>();
                                item.put("event", event.getName());
                                item.put("type", "Type: "+ event.type);

                                newListItems.add(item);
                            }
                        }
                        break;

                }

                updateListView(newListItems, selectedFilter); // refresh listView
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here if nothing is selected
            }
        });
    }
    private static String[] getSelectedMapInfo(String info) {

        // info format: {club=club, event=event}
        String[] keyValPair = info.replaceAll("[{}]", "").split(", ");

        String[] values = new String[keyValPair.length];

        for (int i = 0; i < keyValPair.length; i++) {
            String[] newEntry = keyValPair[i].split("="); // split ex: club=club -> (club, club)
            values[i] = newEntry[1]; // add just the value to the array

            System.out.println(values[i]);
        }
        return values;
    }

    public boolean showEditDeleteOptions(final String club, String event){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Info or Register")
                .setMessage("Choose an action ")
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        registerForEvent(participant, club, event);
                    }
                }).setNegativeButton("Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent infoPage = new Intent(getApplicationContext(), CreatedEventInfo.class);
                        infoPage.putExtra("clubName", club);
                        infoPage.putExtra("eventName", event);
                        startActivity(infoPage);
                    }
                }).show();
        return true;
    }

    // update listView based on filter selected
    public boolean updateListView(List<Map<String, String>> newListItems, String filter) {
        if (newListItems.isEmpty()) {
            noEventsMessage.setVisibility(View.VISIBLE);
            return false;
        } else {
            String[] from = {"event", filter.toLowerCase()};
            int[] to = {R.id.text1, R.id.text2};

            SimpleAdapter adapter = new SimpleAdapter(this, newListItems, R.layout.browse_events_list_item, from, to);
            listView.setAdapter(adapter);
            noEventsMessage.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            return true;
        }
    }

    public void registerForEvent(final String participantName, final String clubName, final String eventName){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Register for " + eventName + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dba.addEventToParticipant(participantName, clubName, eventName);
                        Toast.makeText(ParticipantBrowseEvents.this, "You have registered for " + eventName, Toast.LENGTH_LONG).show();

<<<<<<< HEAD
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }
>>>>>>> 72fd07348a6122c7bcbe2d0c9485f9b331872f13
}
