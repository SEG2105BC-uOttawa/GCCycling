package com.example.gcccyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBClubs dbc = new DBClubs(this);

//        dbc.insertEvent("club", "test1", "test1", "test1", "test1", "test1", "test1");
//        dbc.insertEvent("club", "test2", "test2", "test2", "test2", "test2", "test2");
//        dbc.insertEvent("club", "test3", "test3", "test3", "test3", "test3", "test3");

        // testing getAllClubs_Events
//        Admin admin = new Admin(this);
//        Map<String, Event[]> clubs_events = admin.getAllClubs_Events();
//
//        for (String club : clubs_events.keySet()) {
//            Log.d("Club", club + ": ");
//            for (Event event : clubs_events.get(club)) {
//                Log.d("Event", event.name + " ");
//            }
//        }


    }
    

    public void verify(View view) {
        DBAdmin dbAdmin = new DBAdmin(getApplicationContext());
        EditText username = (EditText) findViewById(R.id.edtTxtUsername);
        EditText password = (EditText) findViewById(R.id.edtTxtPassword);
        String strUsername = username.getText().toString().trim();
        String strPassword = password.getText().toString();
        //place holder to make sure that we can use this if statement, you can change the if conditions if you have to
        //using admin for username and password just to test change to be more general
        if (!Validate.isNotValidUsername(strUsername)) {
            if (strUsername.equals("admin") && strPassword.equals("admin")) {
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.putExtra("name", strUsername);
                i.putExtra("role", "admin");
                startActivity(i);
            } else if (dbAdmin.verifyLogin(strUsername, strPassword)) {
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.putExtra("name", strUsername);
                i.putExtra("role", dbAdmin.getAccountType(strUsername, strPassword));
                Log.d("Role", dbAdmin.getAccountType(strUsername, strPassword));
                startActivity(i);
            } else {
                TextView tv1 = (TextView) findViewById(R.id.txtTryAgain);
                tv1.setText("Either password or username does not match");
            }
        }
        else {
            TextView tv1 = (TextView) findViewById(R.id.txtTryAgain);
            tv1.setText("Illegal username.");
        }
    }

    public void create(View view) {
        Intent i = new Intent(getApplicationContext(), AccountCreation.class);
        startActivity(i);
    }

    public EditText getUsername(){
        EditText username = (EditText) findViewById(R.id.edtTxtUsername);
        return username;
    }
    public EditText getPassword(){
        EditText password = (EditText) findViewById(R.id.edtTxtPassword);
        return password;
    }
}
