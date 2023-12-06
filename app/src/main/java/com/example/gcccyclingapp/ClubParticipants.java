package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ClubParticipants extends AppCompatActivity {
    ListView listView;
    DBAdmin DB;
    DBClubs DBC;
    String clubName;
    String event;
    String participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_club_participant);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DBAdmin(this);
        DBC = new DBClubs(this);
        String[] participantNames = DB.getAllParticipants(clubName);

        if (participantNames.length == 0) {
            participantNames = new String[]{"No participants"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, participantNames);
        listView = (ListView) findViewById(R.id.participantList);
        listView.setAdapter(adapter);
    }

    public void removeFromClub(View view) {
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        Spinner participants = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DB.getAllParticipants(clubName));
        participants.setAdapter(adapter);
        participants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                participant = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        popUp.setTitle("Remove a participant from an event?")
                .setView(participants)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (participant != null) {
                            removeEvent();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    private void removeEvent() {
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        Spinner events = new Spinner(this);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DBC.getAllEvents(clubName));
        events.setAdapter(adapter2);
        events.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                event = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        popUp.setTitle("Which event?")
                .setView(events)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (event != null) {
                            DB.removeEventFromParticipant(participant, clubName, event);
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    public void addAwardPopUp(View view){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        Spinner participants = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DB.getAllParticipants(clubName));
        participants.setAdapter(adapter);
        participants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                participant = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        popUp.setTitle("Send an award to: ")
                .setView(participants)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        enterAward();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    private void enterAward() {
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        EditText awardNameInput = new EditText(this);
        popUp.setTitle("Enter the award: ")
            .setView(awardNameInput)
            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DB.addAwardToParticipant(participant, awardNameInput.getText().toString());
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // don't do anything.
                }
            }).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}