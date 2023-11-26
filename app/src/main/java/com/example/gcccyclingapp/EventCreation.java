package com.example.gcccyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventCreation extends AppCompatActivity {

    String clubName;
    Button viewParticipantsBtn;
    Button sendAwardsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_creation);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }
        else {
            clubName = null;
        }

        TextView welcometxt = (TextView) findViewById(R.id.txtWelcomeMsg);
        welcometxt.setText("Welcome " + clubName + "!");

        viewParticipantsBtn = findViewById(R.id.viewClubParticipantsBtn);
        sendAwardsBtn = findViewById(R.id.sendAwardsBtn);

        viewParticipantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventCreation.this, ClubParticipants.class);
                intent.putExtra("name", clubName);
                startActivity(intent);
            }
        });
        sendAwardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventCreation.this, ClubAwards.class);
                intent.putExtra("name", clubName);
                startActivity(intent);
            }
        });

    }


}