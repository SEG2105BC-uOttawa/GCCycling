package com.example.gcccyclingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class DBAdmin extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "admin.db";

    public static final String CLUB_TABLE = "CLUB_TABLE";
    public static final String CLUB_NAME = "CLUB_NAME";
    public static final String CLUB_USERNAME = "CLUB_USERNAME";
    public static final String CLUB_PASSWORD = "CLUB_PASSWORD";

    public static final String PARTICIPANT_TABLE = "PARTICIPANT_TABLE";
    public static final String PARTICIPANT_NAME = "PARTICIPANT_NAME";
    public static final String PARTICIPANT_USERNAME = "PARTICIPANT_USERNAME";
    public static final String PARTICIPANT_PASSWORD = "PARTICIPANT_PASSWORD";

    public static final String EVENTS_TABLE = "EVENTS_TABLE";
    public static final String EVENT_TYPE = "EVENT_TYPE";
    public static final String EVENT_AGE = "EVENT_AGE";
    public static final String EVENT_PACE = "EVENT_PACE";
    public static final String EVENT_LEVEL = "EVENT_LEVEL";
    public static final String EVENT_LOCATION = "EVENT_LOCATION";
    public static final String EVENT_TIME = "EVENT_TIME";
    public static final String EVENT_DETAILS = "EVENT_DETAILS";







    public DBAdmin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        String createClubTableStatement = "CREATE TABLE " + CLUB_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CLUB_NAME +" TEXT, " + CLUB_USERNAME +" TEXT, " + CLUB_PASSWORD + " TEXT)";
        String createParticipantTableStatement = "CREATE TABLE " + PARTICIPANT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PARTICIPANT_NAME + " TEXT, " + PARTICIPANT_USERNAME +" TEXT, " + PARTICIPANT_PASSWORD + " TEXT)";
        String createEventTableStatement = "CREATE TABLE " + EVENTS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EVENT_TYPE +" TEXT, "
                + EVENT_AGE +" TEXT, "
                + EVENT_PACE + " TEXT, "
                + EVENT_LEVEL + " TEXT, "
                + EVENT_LOCATION + " TEXT, "
                + EVENT_TIME + " TEXT, "
                + EVENT_DETAILS + " TEXT)";

        Log.d("Create event table", createEventTableStatement);

        db.execSQL(createClubTableStatement);
        db.execSQL(createParticipantTableStatement);
        db.execSQL(createEventTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CLUB_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PARTICIPANT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);

        // Recreate the tables
        onCreate(sqLiteDatabase);
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

    public void insertEvent(String eventType, String eventAge, String eventPace, String eventLevel, String eventLocation, String eventTime, String eventDetails){
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        ContentValues cv = new ContentValues();

        cv.put(EVENT_TYPE, eventType);
        cv.put(EVENT_AGE, eventAge);
        cv.put(EVENT_PACE, eventPace);
        cv.put(EVENT_LEVEL, eventLevel);
        cv.put(EVENT_LOCATION, eventLocation);
        cv.put(EVENT_TIME, eventTime);
        cv.put(EVENT_DETAILS, eventDetails);

        Log.d("DB", "Event added");


        db.insert(EVENTS_TABLE, null, cv);
    }

    public boolean verifyLogin(String username, String pwd){
        SQLiteDatabase db = this.getReadableDatabase();

        username = username.trim();

        Log.d("user", username);
        Log.d("pwd", pwd);

        Cursor cursorP = db.rawQuery("SELECT 1 FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME + " = ? AND " + PARTICIPANT_PASSWORD + " = ?", new String[]{username, pwd});
        Cursor cursorC = db.rawQuery("SELECT 1 FROM " + CLUB_TABLE + " WHERE " + CLUB_USERNAME + " = ? AND " + CLUB_PASSWORD + " = ?", new String[]{username, pwd});


        if (cursorP.getCount() > 0 || cursorC.getCount() > 0){
            String countP = String.valueOf(cursorP.getCount());
            Log.d("count", countP);
            cursorP.close();
            cursorC.close();
            Log.d("message", "True credentials");
            return true;
        }
        String countP = String.valueOf(cursorP.getCount());
        Log.d("count", countP);
        cursorP.close();
        cursorC.close();
        Log.d("message", "False credentials");
        return false;
    }

    public String getAccountType(String username, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursorP = db.rawQuery("SELECT COUNT(1) FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME + " = ? AND " + PARTICIPANT_PASSWORD + " = ?", new String[]{username, pwd});
        Cursor cursorC = db.rawQuery("SELECT COUNT(1) FROM " + CLUB_TABLE + " WHERE " + CLUB_USERNAME + " = ? AND " + CLUB_PASSWORD + " = ?", new String[]{username, pwd});

        if (cursorP.getCount() > 0){
            cursorP.close();
            cursorC.close();
            return "Participant";
        } else{
            cursorP.close();
            cursorC.close();
            return "Club";
        }
    }

    public String[] getAllClubs(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorClubs = db.rawQuery("SELECT " + CLUB_USERNAME + " FROM " + CLUB_TABLE, null);
        String[] clubNames = new String[cursorClubs.getCount()];

        int i = 0;
        while (cursorClubs.moveToNext()){
            clubNames[i] = cursorClubs.getString(0); //only one column selected
            i++;
        }
        cursorClubs.close();
        return clubNames;
    }
    public String[] getAllParticipants(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorParticipants = db.rawQuery("SELECT " + PARTICIPANT_USERNAME + " FROM " + PARTICIPANT_TABLE, null);
        String[] participantsNames = new String[cursorParticipants.getCount()];

        int i = 0;
        while (cursorParticipants.moveToNext()){
            participantsNames[i] = cursorParticipants.getString(0); //only one column selected
            i++;
        }
        cursorParticipants.close();
        return participantsNames;
    }
    public String[] getAllEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvents = db.rawQuery("SELECT "+ EVENT_TYPE + " FROM " + EVENTS_TABLE, null);
        String[] eventIDs = new String[cursorEvents.getCount()];

        int i = 0;
        while (cursorEvents.moveToNext()){
            eventIDs[i] = cursorEvents.getString(0); //only one column selected
            i++;
        }
        cursorEvents.close();
        return eventIDs; // returns array of event ID's, use ID to get event information when putting on screen
    }

    public void deleteClub(String clubName){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        Log.d("function", "deleteClub");

        try {
            String delClubStatement = "DELETE FROM " + CLUB_TABLE + " WHERE " + CLUB_USERNAME +" = '" + clubName + "'";
            db.execSQL(delClubStatement);
            Log.d("Deleted", clubName+" was removed from database");
        } catch (Exception e){
            Log.d("Error", "Can't delete null value");
        }
    }
    public void deleteParticipant(String participant){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions

        try {
            String deleteStatement = "DELETE FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME +" = '" + participant + "'";
            db.execSQL(deleteStatement);
            Log.d("Deleted", participant+" was removed from database");
        } catch (Exception e){
            Log.d("Error", "Can't delete null value");
        }
    }
    public void deleteEvent(String eventType){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions

        try {
            String deleteStatement = "DELETE FROM " + EVENTS_TABLE + " WHERE " + EVENT_TYPE + " = '"+ eventType + "'";
            db.execSQL(deleteStatement);
            Log.d("Deleted", "EventID " + eventType + " was removed from database");
        } catch (Exception e){
            Log.d("Error", "Can't delete null value");
        }
    }

    public void updateEvent(String eventType, String category, String info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        switch(category){
            case "type":
                cv.put(EVENT_TYPE, info);
                break;
            case "age":
                cv.put(EVENT_AGE, info);
                break;
            case "pace":
                cv.put(EVENT_PACE, info);
                break;
            case "level":
                cv.put(EVENT_LEVEL, info);
                break;
            case "location":
                cv.put(EVENT_LOCATION, info);
                break;
            case "time":
                cv.put(EVENT_TIME, info);
                break;
            case "details":
                cv.put(EVENT_DETAILS, info);
                break;
        }

        db.update(EVENTS_TABLE, cv, "EVENT_TYPE = ?", new String[]{eventType});

    }

}
