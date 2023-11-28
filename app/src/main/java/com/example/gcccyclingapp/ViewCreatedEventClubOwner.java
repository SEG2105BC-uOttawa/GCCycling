package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewCreatedEventClubOwner extends AppCompatActivity {

    String clubName;
    ListView listView;
    DBClubs DB;
    String[] createdEvents;

    private static final int EDIT_CREATED_EVENT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_created_event_club_owner);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }
        else {
            clubName = null;
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DBClubs(this);

        createdEvents = DB.getAllEvents(clubName);

        Log.d("createdEvents", createdEvents[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.event_list_item, R.id.event, createdEvents);
        listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);

        unregisterForContextMenu(listView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final String selectedEventType = (String) adapterView.getItemAtPosition(position);

                showEditDeleteOptions(selectedEventType);
            }
        });

    }

    private void showEditDeleteOptions(final String createdEvent){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Info, Delete, or Edit")
                .setMessage("Choose an action ")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent editPage = new Intent(getApplicationContext(), EditCreatedEvent.class);
                        editPage.putExtra("name", createdEvent);
//                        startActivity(editPage);
                        startActivityForResult(editPage, EDIT_CREATED_EVENT_REQUEST_CODE);
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteConfirmationPopUp(createdEvent);
                    }
                }).setNeutralButton("Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent infoPage = new Intent(getApplicationContext(), CreatedEventInfo.class);
                        infoPage.putExtra("name", createdEvent);
                        startActivity(infoPage);
                    }
                }).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_CREATED_EVENT_REQUEST_CODE && resultCode == RESULT_OK) {
            refreshListView();
        }
    }

    private void deleteConfirmationPopUp(final String createdEvent){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Confirm Deletion")
                .setIcon(android.R.drawable.ic_delete)
                .setMessage("Are you sure you want to delete event " + createdEvent + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleteEvent(clubName, createdEvent);
                        refreshListView();
                        Toast.makeText(ViewCreatedEventClubOwner.this, "Event " + createdEvent + " has been deleted.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    void refreshListView(){

        String[] createdEventsRefresh = DB.getAllEvents(clubName);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.event_list_item, R.id.event, createdEventsRefresh);
        listView.setAdapter(adapter);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}