package com.example.gcccyclingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantBrowseEvents extends AppCompatActivity {

    private Event[] events;
    private String[] clubs;
    private List<String> participantsClubs;
    Map<String, Event[]> clubsEvents;
    private DBClubs dbc;
    private DBAdmin dba;
    Admin admin;
    ListView listView;
    TextView noEventsMessage;
    String participant;


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

        participantsClubs = dba.getParticipantClubs(participant); // array of all clubs the participant is part of

        clubs = dba.getAllClubs();

        for (String club : clubs) {
            events = dbc.getAllEventsObject(club); // get all event names in String[] format
            clubsEvents.put(club, events); // each key (club name) has an array of Events
        }

        List<Map<String, String>> listItems = new ArrayList<>();
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
                        item.put("club", club + " - You are part of this club!");
                    } else {
                        item.put("club", club);
                    }
                } else { // if the participant is not in any clubs, just format everything normally
                    item.put("club", club);
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

            listView = (ListView) findViewById(R.id.browseEventsList);
            listView.setVisibility(View.VISIBLE);
            SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.browse_events_list_item, from, to);
            listView.setAdapter(adapter);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Map<String, String> selectedMap = (Map<String, String>) adapterView.getItemAtPosition(position);

                String info = selectedMap.toString();
                System.out.println(info);

                String[] infoArray = getSelectedMapInfo(info);

                String club = infoArray[0];
                String event = infoArray[1];


                showEditDeleteOptions(club, event);
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

    private void showEditDeleteOptions(final String club, String event){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Info or Register")
                .setMessage("Choose an action ")
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent editPage = new Intent(getApplicationContext(), EditCreatedEvent.class);
//                        editPage.putExtra("clubName", club);
//                        editPage.putExtra("eventName", event);
//                        startActivity(editPage);
//                        startActivityForResult(editPage, EDIT_CREATED_EVENT_REQUEST_CODE);
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
    }

}
