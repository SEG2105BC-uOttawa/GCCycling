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
    public static final String CLUB_PARTICIPANT_NAME = "PARTICIPANT_NAME"; // column listing participants in club
    public static final String CLUB_PARTICIPANT_USERNAME = "PARTICIPANT_USERNAME";
    public static final String CLUB_PARTICIPANT_PASSWORD = "PARTICIPANT_PASSWORD";


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
        String createTableStatement = "CREATE TABLE " + clubName +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLUB_PARTICIPANT_NAME +" TEXT, " +
                CLUB_PARTICIPANT_USERNAME +" TEXT, " +
                CLUB_PARTICIPANT_PASSWORD +" TEXT)";

        db.execSQL(createTableStatement);
        db.close();

        Log.d("Club", clubName+" created");
    }
//    public void deleteTable(String clubName){
//        SQLiteDatabase db = getWritableDatabase();
//        String deleteTableStatement = "DROP TABLE " + clubName;
//        db.execSQL(deleteTableStatement);
//        db.close();
//
//        Log.d("Club", clubName+" deleted");
//    }

    public void addParticipant(String clubName, String participantName, String participantUser, String participantPWD){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CLUB_PARTICIPANT_NAME, participantName); // inserts data into club column
        cv.put(CLUB_PARTICIPANT_USERNAME, participantUser);
        cv.put(CLUB_PARTICIPANT_PASSWORD, participantPWD);
        db.insert(clubName, null, cv);

        Log.d("Data Insert", "Participant added to "+clubName);
    }
//    public void deleteParticipant(String clubName, String participantName){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.delete(clubName, null, null);
//        db.execSQL("DELETE FROM " + clubName + " WHERE " + CLUB_PARTICIPANT_NAME + " = " + participantName);
//        db.close();
//
//        Log.d("Data Deleted", participantName+" deleted");
//    }






}
