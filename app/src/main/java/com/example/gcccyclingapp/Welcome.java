package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    int LAUNCH_EVENT_CREATION = 1;
    Button btnParticipant;
    Button btnClub;
    Button btnEvent;
    Button btnComplete;
    public Admin admin;

    private DBAdmin db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcome_screen);

            db = new DBAdmin(this);
            String name = null;
            String role = null;

            Bundle bundle = getIntent().getExtras();
            if(bundle.getString("name")!=null && bundle.getString("role") != null){
                name = bundle.getString("name");
                role = bundle.getString("role");
            }

            TextView welcometxt = (TextView) findViewById(R.id.txtWelcome);
            welcometxt.setText("Welcome " + name + "!\nYou are logged in as " + role);

            if(role.equals("admin")){
                admin = new Admin(this);
                Button setEvent = (Button) findViewById(R.id.createEventBtn);
                setEvent.setVisibility(View.VISIBLE);
            }

            if(role.equals("admin")) {
                Button setParticipantsBtn = (Button) findViewById(R.id.viewParticipantsBtn);
                Button setClubsBtn = (Button) findViewById(R.id.viewClubsBtn);
                Button setEventBtn = (Button) findViewById(R.id.viewEventsBtn);

                setParticipantsBtn.setVisibility(View.VISIBLE);
                setClubsBtn.setVisibility(View.VISIBLE);
                setEventBtn.setVisibility(View.VISIBLE);
            }

            if(role.equals("Club")){
                String[] info = db.getClubInfo(name);

                if (info == null) {
                    Log.d("Logged in", "Club owner");
                    Button setEvent = (Button) findViewById(R.id.completeClubBtn);
                    setEvent.setVisibility(View.VISIBLE);
                }
                else {
                    Intent intent = new Intent(Welcome.this, EventCreation.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            }

            btnParticipant = findViewById(R.id.viewParticipantsBtn);
            btnClub = findViewById(R.id.viewClubsBtn);
            btnEvent = findViewById(R.id.viewEventsBtn);
            btnComplete = findViewById(R.id.completeClubBtn);
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

            btnEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Welcome.this, ViewEventType.class);
                    startActivity(intent);
                }
            });

            btnComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Welcome.this, CompleteAccountManager.class);

                    String name = bundle.getString("name");
                    intent.putExtra("clubUser", name);

                    startActivity(intent);
                }
            });
        }

        public void createEvent(View view) {
            Intent i = new Intent(getApplicationContext(), EventTypeCreation.class);
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
