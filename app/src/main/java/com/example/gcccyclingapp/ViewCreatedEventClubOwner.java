package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewCreatedEventClubOwner extends AppCompatActivity {

    String clubName;
    ListView listView;
    DBClubs DB;
    Event[] createdEvents;

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

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.event_list_item, R.id.event, createdEvents);
        listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);

        unregisterForContextMenu(listView);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}