package com.example.gcccyclingapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcome_screen);
            MainActivity MA = new MainActivity();
            TextView tv1 = (TextView) findViewById(R.id.txtWelcome);
            String name = MA.getUsername().toString();
            String role = "admin";
            tv1.setText("Welcome " + name + "you are logged in as " + role);
        }


}
