package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    int LAUNCH_EVENT_CREATION = 1;
    Button btnParticipant;
    Button btnClub;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcome_screen);

            String name = null;
            String role = null;

            Bundle bundle = getIntent().getExtras();
            if(bundle.getString("name")!=null && bundle.getString("role") != null){
                name = bundle.getString("name");
                role = bundle.getString("role");
            }

            TextView welcometxt = (TextView) findViewById(R.id.txtWelcome);
            welcometxt.setText("Welcome " + name + " you are logged in as " + role);

            if(role.equals("admin")){
                Button setEvent = (Button) findViewById(R.id.createEventBtn);
                setEvent.setVisibility(View.VISIBLE);
            }

            if(role.equals("admin")) {
                Button setEvent = (Button) findViewById(R.id.viewParticipantsBtn);
                setEvent.setVisibility(View.VISIBLE);
            }

            if(role.equals("admin")) {
                Button setEvent = (Button) findViewById(R.id.viewClubsBtn);
                setEvent.setVisibility(View.VISIBLE);
            }

            btnParticipant = findViewById(R.id.viewParticipantsBtn);
            btnClub = findViewById(R.id.viewClubsBtn);
            btnParticipant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Welcome.this, ViewParticipant.class);
                    startActivity(intent);
                }
            });

            btnClub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Welcome.this, ViewClub.class);
                    startActivity(intent);
                }
            });
        }

        public void createEvent(View view) {
            Intent i = new Intent(getApplicationContext(), eventCreation.class);
            startActivityForResult(i, LAUNCH_EVENT_CREATION);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_EVENT_CREATION) {
            if (resultCode == RESULT_OK) {
                String age = data.getStringExtra("age");
                String pace = data.getStringExtra("pace");
                String level = data.getStringExtra("level");
                String location = data.getStringExtra("location");
                String eventType = data.getStringExtra("event_type");
                String time = data.getStringExtra("time");
                String details = data.getStringExtra("details");
            }
        }


    }




}
