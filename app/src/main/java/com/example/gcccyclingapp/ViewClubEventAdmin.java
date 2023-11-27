package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewClubEventAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_club_event_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Map<String, Event[]> listItems = new HashMap<>();
//        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.club_event_list_item,
//                new String[]{"First Line", "Second Line"},
//                new int[]{R.id.text1, R.id.text2});


//        Iterator iterator = nameAddresses.entrySet().iterator();
//        while (it.hasNext())
//        {
//            HashMap<String, String> resultsMap = new HashMap<>();
//            Map.Entry pair = (Map.Entry)it.next();
//            resultsMap.put("First Line", pair.getKey().toString());
//            resultsMap.put("Second Line", pair.getValue().toString());
//            listItems.add(resultsMap);
//        }

//        resultsListView.setAdapter(adapter);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}