package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    int LAUNCH_EVENT_CREATION = 1;

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
        }

        public void createEvent(View view) {
            Intent i = new Intent(getApplicationContext(), eventCreation.class);
            startActivityForResult(i, LAUNCH_EVENT_CREATION);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_EVENT_CREATION) {
            if(resultCode == RESULT_OK){
                String age=data.getStringExtra("age");
                String pace=data.getStringExtra("pace");
                String level=data.getStringExtra("level");
                String location=data.getStringExtra("location");
                String time=data.getStringExtra("time");
                String details=data.getStringExtra("details");
                String eventType=data.getStringExtra("event_type");
            }
        }
    }




}
