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
    public static final String CLUB_NAME = "CLUB_NAME";
    public static final String CLUB_LINK = "CLUB_LINK";
    public static final String CLUB_CONTACT = "CLUB_CONTACT";
    public static final String CLUB_PHONE = "CLUB_PHONE";


    public DBClubs(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        // start with no tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS *");
//
//
//        // Recreate the tables
//        onCreate(sqLiteDatabase);
    }

    public void createTable(String clubName){
        SQLiteDatabase db = getWritableDatabase();
        String createTableStatement = "CREATE TABLE " + clubName +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLUB_NAME +" TEXT, " +
                CLUB_LINK +" TEXT, " +
                CLUB_CONTACT +" TEXT, " +
                CLUB_PHONE +" TEXT)";

        db.execSQL(createTableStatement);


        db.close();

        Log.d("Club", clubName+" created");
    }
    public void deleteClub(String clubName){
        Log.d("Club Name to be deleted", clubName);
        SQLiteDatabase db = getWritableDatabase();
        String deleteTableStatement = "DROP TABLE " + clubName;
        db.execSQL(deleteTableStatement);
        db.close();

        Log.d("Club", clubName+" deleted");
    }

    public void completeClubAccount(String clubName, String link, String contactName, String phone){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("DB", "Club updated");

        db.execSQL("UPDATE "+ clubName + " SET " + CLUB_LINK + " = '" + link + "'");
        db.execSQL("UPDATE "+ clubName + " SET " + CLUB_CONTACT + " = '" + contactName + "'");
        db.execSQL("UPDATE "+ clubName + " SET " + CLUB_PHONE + " = '" + phone + "'");
    }

//    public void addParticipant(String clubName, String clubName, String participantUser, String participantPWD){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(CLUB_PARTICIPANT_NAME, participantName); // inserts data into club column
//        cv.put(CLUB_PARTICIPANT_USERNAME, participantUser);
//        cv.put(CLUB_PARTICIPANT_PASSWORD, participantPWD);
//        db.insert(clubName, null, cv);
//
//        Log.d("Data Insert", "Participant added to "+clubName);
//    }
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
