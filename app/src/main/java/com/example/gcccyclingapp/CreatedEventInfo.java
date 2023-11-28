package com.example.gcccyclingapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CreatedEventInfo extends AppCompatActivity {

    String clubName;
    String eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_event_info);

        DBClubs db = new DBClubs(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }
        else {
            clubName = null;
        }

        if(bundle.getString("event")!=null){
            eventName = bundle.getString("event");
        }
        else {
            eventName = null;
        }

        TextView name = (TextView) findViewById(R.id.eventNametxt);
        TextView type = (TextView) findViewById(R.id.typetxt);
        TextView difficulty = (TextView) findViewById(R.id.difficultytxt);
        TextView fees = (TextView) findViewById(R.id.feestxt);
        TextView limit = (TextView) findViewById(R.id.participanttxt);
        TextView date = (TextView) findViewById(R.id.datetxt);
        TextView route = (TextView) findViewById(R.id.detailstxt);

        String[] info = db.getEventInfo(clubName, eventName);

        name.setText(eventName);
        type.setText(info[2]);
        difficulty.setText(info[3]);
        fees.setText(info[4]);
        limit.setText(info[5]);
        date.setText(info[6]);
        route.setText(info[7]);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}