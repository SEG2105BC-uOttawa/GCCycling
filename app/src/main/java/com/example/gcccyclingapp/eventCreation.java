package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class eventCreation extends AppCompatActivity {

    String eventSelected;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.create_event);

            Spinner eventType = findViewById(R.id.eventType);
            String[] event_types = {"TT", "Hill Climb", "Road Stage Race", "Road Race", "Group Rides"};
            ArrayAdapter ad = new ArrayAdapter (this, android.R.layout.simple_spinner_item, event_types);

            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            eventType.setAdapter(ad);

        }
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            eventSelected = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {}

        public void set() {
            TextView age = findViewById(R.id.agetxt);
            TextView pace = findViewById(R.id.pacetxt);
            TextView level = findViewById(R.id.leveltxt);
            TextView location = findViewById(R.id.locationtxt);
            TextView time = findViewById(R.id.timetxt);
            TextView details = findViewById(R.id.detailstxt);

            String strAge = age.getText().toString();
            String strPace = pace.getText().toString();
            String strLevel = level.getText().toString();
            String strLocation = location.getText().toString();
            String strTime = time.getText().toString();
            String strDetails = details.getText().toString();
        }





}
