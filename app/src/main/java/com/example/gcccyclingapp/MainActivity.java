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

    DBAdmin dbAdmin;
    DBClubs dbClubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdmin = new DBAdmin(getApplicationContext());
        dbClubs = new DBClubs((getApplicationContext()));


        // preload club account
        dbAdmin.insertClub("gccadmin", "gccadmin", "GCCRocks!");
        dbClubs.addClub("gccadmin");

        // preload participant account
        dbAdmin.insertParticipant("cyclingaddict", "cyclingaddict", "cyclingIsLife!");

        // preload event types
        dbAdmin.insertEvent("Time Trial", "18", "10", "10", "Ottawa", "Morning", "Time trials, often referred to as \"TTs,\" are individual races against\n" +
                "the clock. Cyclists start at intervals and race alone to complete a set course as\n" +
                "quickly as possible. It's a test of a rider's ability to maintain a consistent pace and\n" +
                "maximize speed.");
        dbAdmin.insertEvent("Hill Climb", "18", "10", "10", "Ottawa", "Morning", "Hill climbing events challenge cyclists to ascend steep inclines or\n" +
                "mountains as quickly as possible. Participants showcase their climbing abilities,\n" +
                "endurance, and strength during these challenging races.\n");
        dbAdmin.insertEvent("Road Stage Race", "18", "10", "10", "Ottawa", "Morning", "Road stage races are multi-day events composed of multiple\n" +
                "stages, each with its own route and terrain. Cyclists compete over several days,\n" +
                "and the overall winner is determined by the lowest cumulative time across all\n" +
                "stages. Events like the Tour de France are classic examples");
    }
    

    public void verify(View view) {

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
                i.putExtra("password", strPassword);
                i.putExtra("role", dbAdmin.getAccountType(strUsername, strPassword));
                Log.d("Role", dbAdmin.getAccountType(strUsername, strPassword));
                startActivity(i);
            }
            else {
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
