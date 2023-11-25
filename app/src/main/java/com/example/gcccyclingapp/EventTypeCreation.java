package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EventTypeCreation extends AppCompatActivity {

    String eventSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_type);

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

        Boolean check = true;
        String warningText = "";

        if (Validate.isNotAlphaNumeric(strLocation)) {
            warningText += "Location is invalid. ";
            check = false;
        }
        if (Validate.isNotValidTime(strTime)) {
            warningText += "Time is invalid. ";
            check = false;
        }
        if (strDetails.equals("")) {
            warningText += "Details is empty. ";
            check = false;
        }

        TextView warning = (TextView) findViewById(R.id.warningtxt);
        warning.setText(warningText);

        if (Validate.isNotNumeric(strAge)) {
            strAge = "25";
        }
        if (Validate.isNotNumeric(strPace)) {
            strPace = "20";
        }
        if (Validate.isNotNumeric(strLevel)) {
            strLevel = "5";
        }

        if (check) {
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

            Toast.makeText(EventTypeCreation.this, "Event type " + strType + " has been created.", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
