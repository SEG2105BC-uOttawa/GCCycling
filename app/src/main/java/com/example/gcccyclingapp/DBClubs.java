package com.example.gcccyclingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBClubs extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "clubs.db";
    public static final String CLUB_PARTICIPANTS_COLUMN = "CLUB_PARTICIPANTS"; // column listing participants in club



    public DBClubs(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        // start with no tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes

    }

    public void createTable(String clubName){
        SQLiteDatabase db = getWritableDatabase();
        String createTableStatement = "CREATE TABLE " + clubName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CLUB_PARTICIPANTS_COLUMN +" TEXT)";
        db.execSQL(createTableStatement);

        Log.d("New Club", "CLub created");
    }

    public void addParticipant(String clubName, String participantName){
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        ContentValues cv = new ContentValues();

        cv.put(CLUB_PARTICIPANTS_COLUMN, participantName); // inserts data into club column
        db.insert(clubName, null, cv);

        Log.d("Data Insert", "Participant added to "+clubName);
    }

    // delete table
    // update club participants list (add / delete to tables)




}
