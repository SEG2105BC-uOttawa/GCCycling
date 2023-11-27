package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateNewEventClubOwner extends AppCompatActivity {

    String clubName;
    DBAdmin DB;
    String[] eventTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event_club_owner);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        Spinner eventTypeSpinner = findViewById(R.id.eventTypetxt);
        displayEventTypeSpinner(eventTypeSpinner);

        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }
        else {
            clubName = null;
        }
    }

    private void displayEventTypeSpinner(Spinner spinner){

        DB = new DBAdmin(this);
        eventTypes = DB.getAllEvents();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.event_list_item, R.id.event, eventTypes);
        spinner = (Spinner) findViewById(R.id.eventTypetxt);
        spinner.setAdapter(adapter);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void set(View view) {
        DBClubs db = new DBClubs(this);

        EditText name = (EditText) findViewById(R.id.eventNametxt);
        Spinner type = (Spinner) findViewById(R.id.eventTypetxt);
        EditText difficulty = (EditText) findViewById(R.id.difficultytxt);
        EditText fees = (EditText) findViewById(R.id.feestxt);
        EditText limit = (EditText) findViewById(R.id.limittxt);
        EditText date = (EditText) findViewById(R.id.datetxt);
        EditText route = (EditText) findViewById(R.id.detailstxt);

        String strName = name.getText().toString();
        String strType = type.getSelectedItem().toString();
        String strDifficulty = difficulty.getText().toString();
        String strFees = fees.getText().toString();
        String strLimit = limit.getText().toString();
        String strDate = date.getText().toString();
        String strDetails = route.getText().toString();


        Boolean check = true;
        String warningText = "";

        if (strName.equals("")) {
            warningText += "Name is empty. ";
            check = false;
        }
        if (strType.equals("")) {
            warningText += "Selected Type is empty. ";
            check = false;
        }
        if (strFees.equals("")) {
            warningText += "Fees is empty. ";
            check = false;
        }
        if (strLimit.equals("")) {
            warningText += "Participant Limit is empty. ";
            check = false;
        }
        if (strDate.equals("")) {
            warningText += "Date is empty. ";
            check = false;
        }
        if (strDetails.equals("")) {
            warningText += "Route Details is empty. ";
            check = false;
        }
        TextView warning = (TextView) findViewById(R.id.warningtxt);
        warning.setText(warningText);

        if (check) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("name", strName);
            returnIntent.putExtra("type", strType);
            returnIntent.putExtra("difficulty", strDifficulty);
            returnIntent.putExtra("fees", strFees);
            returnIntent.putExtra("limit", strLimit);
            returnIntent.putExtra("date", strDate);
            returnIntent.putExtra("route", strDetails);
            setResult(RESULT_OK, returnIntent);
            Log.d("Event Created Of Type", strType);

            db.insertEvent(clubName, strName, strType, strDifficulty, strFees, strLimit, strDate, strDetails);

            Toast.makeText(CreateNewEventClubOwner.this, "Event of type " + strType + " has been created.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}