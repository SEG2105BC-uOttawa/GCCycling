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

public class EventCreation extends AppCompatActivity {

    String eventSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void set(View view) {
        DBAdmin db = new DBAdmin(this);
        EditText type = (EditText) findViewById(R.id.eventTypetxt);
        EditText age = (EditText) findViewById(R.id.agetxt);
        EditText pace = (EditText) findViewById(R.id.pacetxt);
        EditText level = (EditText) findViewById(R.id.leveltxt);
        EditText location = (EditText) findViewById(R.id.locationtxt);
        EditText time = (EditText) findViewById(R.id.timetxt);
        EditText details = (EditText) findViewById(R.id.detailstxt);
//        Spinner event = (Spinner) findViewById(R.id.eventType);

        String strType = type.getText().toString();
        String strAge = age.getText().toString();
        String strPace = pace.getText().toString();
        String strLevel = level.getText().toString();
        String strLocation = location.getText().toString();
        String strTime = time.getText().toString();
        String strDetails = details.getText().toString();
//        event.setOnItemSelectedListener(this);

        Boolean check = false;

        if (strLocation.equals("") || strTime.equals("") || strDetails.equals("")) {
            TextView warning = (TextView) findViewById(R.id.warningtxt);
            warning.setText("Either Location, Time, or Details are missing please fill those out");
        } else {
            check = true;
        }

        if (strAge.equals("")) {
            strAge = "25";
        }
        if (strPace.equals("")) {
            strPace = "20";
        }
        if (strLevel.equals("")) {
            strLevel = "5";
        }

        if (check == true) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("type", strAge);
            returnIntent.putExtra("age", strAge);
            returnIntent.putExtra("pace", strPace);
            returnIntent.putExtra("level", strLevel);
            returnIntent.putExtra("location", strLocation);
            returnIntent.putExtra("time", strTime);
            returnIntent.putExtra("details", strDetails);
//            returnIntent.putExtra("event_type", eventSelected);
            setResult(RESULT_OK, returnIntent);
            Log.d("Event", strType);
            db.insertEvent(strType, strAge, strPace, strLevel, strLocation, strTime, strDetails);
            Toast.makeText(EventCreation.this, "Event type " + strType + " has been created.", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
