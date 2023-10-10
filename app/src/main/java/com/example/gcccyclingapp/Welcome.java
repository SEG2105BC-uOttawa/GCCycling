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


}
