package com.example.gcccyclingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBAdmin extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "admin.db";
    public static final String CLUB_TABLE = "CLUB_TABLE";
    public static final String PARTICIPANT_TABLE = "PARTICIPANT_TABLE";
    public static final String CLUB_NAME = "CLUB_NAME";
    public static final String CLUB_USERNAME = "CLUB_USERNAME";
    public static final String CLUB_PASSWORD = "CLUB_PASSWORD";
    public static final String PARTICIPANT_NAME = "PARTICIPANT_NAME";
    public static final String PARTICIPANT_USERNAME = "PARTICIPANT_USERNAME";
    public static final String PARTICIPANT_PASSWORD = "PARTICIPANT_PASSWORD";



    public DBAdmin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        String createClubTableStatement = "CREATE TABLE " + CLUB_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CLUB_NAME +" TEXT, " + CLUB_USERNAME +" TEXT, " + CLUB_PASSWORD + " TEXT)";
        String createParticipantTableStatement = "CREATE TABLE " + PARTICIPANT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PARTICIPANT_NAME + " TEXT, " + PARTICIPANT_USERNAME +" TEXT, " + PARTICIPANT_PASSWORD + " TEXT)";

        db.execSQL(createClubTableStatement);
        db.execSQL(createParticipantTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes

    }

    public void insertClub(String clubName, String clubUser, String clubPWD){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        ContentValues cv = new ContentValues();

        cv.put(CLUB_NAME, clubName); // inserts data into club column
        cv.put(CLUB_USERNAME, clubUser); // inserts data into club column
        cv.put(CLUB_PASSWORD, clubPWD); // inserts data into club column

        db.insert(CLUB_TABLE, null, cv);
    }
    public void insertParticipant(String participantName, String participantUser, String participantPWD){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        ContentValues cv = new ContentValues();

        cv.put(PARTICIPANT_NAME, participantName); // inserts data into club column
        cv.put(PARTICIPANT_USERNAME, participantUser); // inserts data into club column
        cv.put(PARTICIPANT_PASSWORD, participantPWD); // inserts data into club column

        db.insert(PARTICIPANT_TABLE, null, cv);
    }

    public void verifyLogin(String username, String pwd){
        // check if username and password are in database, check both tables
    }

//    public void delete(String clubName){ // change to work with Club class
//        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
//
//        String queryString = "DELETE FROM " + ADMIN_TABLE + " WHERE " + ADMIN_CLUB_NAME + " = " + clubName;
//        Cursor cursor = db.rawQuery(queryString, null);
//
//        db.delete(ADMIN_TABLE, ADMIN_CLUB_NAME+" = "+clubName, null);
//        db.execSQL("DELETE FROM " + ADMIN_TABLE + " WHERE " + ADMIN_CLUB_NAME + " = " + clubName);
//    }

}
