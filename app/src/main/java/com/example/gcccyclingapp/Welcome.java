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
    Button btnEventType;
    Button btnComplete;
    Button btnClubEvent;
    Button btnBrowseClubs;
    public Admin admin;
    Participant p;

    private DBAdmin db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcome_screen);

            db = new DBAdmin(this);
            String name = null;
            String password = null;
            String role = null;

            Bundle bundle = getIntent().getExtras();
            if(bundle.getString("name")!=null && bundle.getString("role")!=null){
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
                Button setClubEventBtn = (Button) findViewById(R.id.viewClubEventsBtn);

                setParticipantsBtn.setVisibility(View.VISIBLE);
                setClubsBtn.setVisibility(View.VISIBLE);
                setEventBtn.setVisibility(View.VISIBLE);
                setClubEventBtn.setVisibility(View.VISIBLE);
            }

            if(role.equals("Club")){

                if (db.getClubInfo(name) == null) {
                    Log.d("Logged in", "Club owner");
                    Button setEvent = (Button) findViewById(R.id.completeClubBtn);
                    setEvent.setVisibility(View.VISIBLE);
                }
                else {
                    String[] info = db.getClubInfo(name);
                    Intent intent = new Intent(Welcome.this, ClubOwnerPage.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }

            }

            if(role.equals("Participant")){
                this.p = new Participant(name, name, password, role, this);
                Button browseEvents = (Button) findViewById(R.id.browseEventsBtn);
                browseEvents.setVisibility(View.VISIBLE);
            }


            btnParticipant = findViewById(R.id.viewParticipantsBtn);
            btnClub = findViewById(R.id.viewClubsBtn);
            btnEventType = findViewById(R.id.viewEventsBtn);
            btnComplete = findViewById(R.id.completeClubBtn);
            btnClubEvent = findViewById(R.id.viewClubEventsBtn);
            btnBrowseClubs = findViewById(R.id.browseEventsBtn);

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

            btnEventType.setOnClickListener(new View.OnClickListener() {
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

            btnClubEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Welcome.this, ViewClubEventAdmin.class);
                    startActivity(intent);
                }
            });

            btnBrowseClubs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Welcome.this, ParticipantBrowseEvents.class);
                    intent.putExtra("participant", p.username);
                    startActivity(intent);
                }
            });
        }

        public void createEvent(View view) {
            Intent i = new Intent(getApplicationContext(), EventTypeCreationAdmin.class);
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
