package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewClubEventAdmin extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_club_event_admin);

        Log.d("Admin Page", "View Club Events Admin");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DBAdmin DBa = new DBAdmin(this);
        DBClubs DBc = new DBClubs(this);
        Admin admin = new Admin(this);

        Map<String, Event[]> clubs_events = admin.getAllClubs_Events();


        List<Map<String, String>> listItems = new ArrayList<>();

        for (String club : clubs_events.keySet()) {
            Event[] events = clubs_events.get(club);
            for (Event event : events) {
                Log.d("event", event.getName());
                Log.d("club", club);
                Map<String, String> item = new HashMap<>();
                item.put("event", event.getName());
                item.put("club", club);
                listItems.add(item);
            }
        }

        String[] from = {"event", "club"};
        int[] to = {R.id.text1, R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.club_event_list_item_admin, from, to);
        listView = (ListView) findViewById(R.id.clubEventList);
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