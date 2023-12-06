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

public class ClubParticipants extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView listView;
    DBAdmin DB;
    String clubName;
    String event;

    String selectedParticipantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participant);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DBAdmin(this);
        String[] participantNames = DB.getAllParticipants(clubName);

        if (participantNames.length == 0) {
            participantNames = new String[]{"No participants"};
        }

        if (participantNames[0] == null) {
            participantNames = new String[]{"No participants"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.account_list_item_club, R.id.list_item, participantNames);
        listView = (ListView) findViewById(R.id.participantList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                selectedParticipantName = (String) parent.getItemAtPosition(position);
            }
        });
    }

    public void removeFromClub() {
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        Spinner events = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DB.getAllEvents());
        events.setAdapter(adapter);
        events.setOnItemSelectedListener(this);

        popUp.setTitle("Remove " + selectedParticipantName + " from an event?")
                .setView(events)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (event != null) {
                            DB.removeEventFromParticipant(selectedParticipantName, clubName, event);
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    public void addAwardPopUp(){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        EditText awardNameInput = new EditText(this);

        popUp.setTitle("Send an award to " + selectedParticipantName)
                .setView(awardNameInput)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.addAwardToParticipant(selectedParticipantName, awardNameInput.getText().toString());
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        event = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}