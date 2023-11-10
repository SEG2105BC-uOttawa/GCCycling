package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class eventCreation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String eventSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        Spinner eventType = findViewById(R.id.eventType);
        String[] event_types = {"TT", "Hill Climb", "Road Stage Race", "Road Race", "Group Rides"};
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, event_types);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        eventType.setAdapter(ad);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        eventSelected = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    public void set(View view) {
        EditText age = (EditText) findViewById(R.id.agetxt);
        EditText pace = (EditText) findViewById(R.id.pacetxt);
        EditText level = (EditText) findViewById(R.id.leveltxt);
        EditText location = (EditText) findViewById(R.id.locationtxt);
        EditText time = (EditText) findViewById(R.id.timetxt);
        EditText details = (EditText) findViewById(R.id.detailstxt);
        Spinner event = (Spinner) findViewById(R.id.eventType);

        String strAge = age.getText().toString();
        String strPace = pace.getText().toString();
        String strLevel = level.getText().toString();
        String strLocation = location.getText().toString();
        String strTime = time.getText().toString();
        String strDetails = details.getText().toString();
        event.setOnItemSelectedListener(this);

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
            returnIntent.putExtra("age", strAge);
            returnIntent.putExtra("pace", strPace);
            returnIntent.putExtra("level", strLevel);
            returnIntent.putExtra("location", strLocation);
            returnIntent.putExtra("time", strTime);
            returnIntent.putExtra("details", strDetails);
            returnIntent.putExtra("event_type", eventSelected);
            setResult(RESULT_OK, returnIntent);
            finish();
        }

    }
}
