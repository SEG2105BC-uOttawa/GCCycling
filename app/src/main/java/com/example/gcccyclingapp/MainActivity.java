package com.example.gcccyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Admin admin = new Admin();

        DBAdmin dbAdmin = new DBAdmin(MainActivity.this);
        DBClubs dbClubs = new DBClubs(MainActivity.this);
//        admin.deleteClub(dbAdmin, dbClubs, "club2"); fix delete
//        admin.createClub(dbAdmin, dbClubs, "club2");
//        dbClubs.addParticipant("club", "bob");



    }
}