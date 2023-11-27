package com.example.gcccyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClubOwnerPage extends AppCompatActivity {

    String clubName;
    Button viewParticipantsBtn;
    Button sendAwardsBtn;
    Button createNewEventBtn;
    Button viewCreatedEventsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_owner_page);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }
        else {
            clubName = null;
        }

        TextView welcometxt = (TextView) findViewById(R.id.txtWelcomeMsg);
        welcometxt.setText("Welcome " + clubName + "!");

        createNewEventBtn = findViewById(R.id.createNewEventBtn2);
        viewCreatedEventsBtn = findViewById(R.id.viewCreatedEventsBtn);
        viewParticipantsBtn = findViewById(R.id.viewClubParticipantsBtn);
        sendAwardsBtn = findViewById(R.id.sendAwardsBtn);

        createNewEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubOwnerPage.this, CreateNewEventClubOwner.class);
                intent.putExtra("name", clubName);
                startActivity(intent);
            }
        });
        viewCreatedEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubOwnerPage.this, ViewCreatedEventClubOwner.class);
                intent.putExtra("name", clubName);
                startActivity(intent);
            }
        });
        viewParticipantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubOwnerPage.this, ClubParticipants.class);
                intent.putExtra("name", clubName);
                startActivity(intent);
            }
        });
        sendAwardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClubOwnerPage.this, ClubAwards.class);
                intent.putExtra("name", clubName);
                startActivity(intent);
            }
        });

    }


}