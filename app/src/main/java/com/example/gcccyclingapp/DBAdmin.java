package com.example.gcccyclingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class DBAdmin extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "admin.db";

    public static final String CLUB_TABLE = "CLUB_TABLE";
    public static final String CLUB_NAME = "CLUB_NAME";
    public static final String CLUB_USERNAME = "CLUB_USERNAME";
    public static final String CLUB_PASSWORD = "CLUB_PASSWORD";
    public static final String CLUB_LINK = "CLUB_LINK";
    public static final String CLUB_CONTACT = "CLUB_CONTACT";
    public static final String CLUB_PHONE = "CLUB_PHONE";

    public static final String PARTICIPANT_TABLE = "PARTICIPANT_TABLE";
    public static final String PARTICIPANT_NAME = "PARTICIPANT_NAME";
    public static final String PARTICIPANT_USERNAME = "PARTICIPANT_USERNAME";
    public static final String PARTICIPANT_PASSWORD = "PARTICIPANT_PASSWORD";
    public static final String PARTICIPANT_AWARDS = "PARTICIPANT_AWARDS";
    public static final String PARTICIPANT_CLUBS = "PARTICIPANT_CLUBS";

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
        String createClubTableStatement = "CREATE TABLE "
                + CLUB_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CLUB_NAME +" TEXT, "
                + CLUB_USERNAME +" TEXT, "
                + CLUB_PASSWORD +" TEXT, "
                + CLUB_LINK +" TEXT, "
                + CLUB_CONTACT +" TEXT, "
                + CLUB_PHONE + " TEXT)";
        String createParticipantTableStatement = "CREATE TABLE " + PARTICIPANT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PARTICIPANT_NAME + " TEXT, "
                + PARTICIPANT_USERNAME +" TEXT, "
                + PARTICIPANT_PASSWORD +" TEXT, "
                + PARTICIPANT_AWARDS +" TEXT, "
                + PARTICIPANT_CLUBS + " TEXT)";
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
        cv.put(PARTICIPANT_AWARDS, "");
        cv.put(PARTICIPANT_CLUBS, "");

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

        Cursor cursorP = db.rawQuery("SELECT 1 FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME + " = ? AND " + PARTICIPANT_PASSWORD + " = ?", new String[]{username, pwd});
        Cursor cursorC = db.rawQuery("SELECT 1 FROM " + CLUB_TABLE + " WHERE " + CLUB_USERNAME + " = ? AND " + CLUB_PASSWORD + " = ?", new String[]{username, pwd});

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
    public String[] getAllParticipants(String clubName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorParticipants = db.rawQuery("SELECT " + PARTICIPANT_USERNAME + ", " + PARTICIPANT_CLUBS + " FROM " + PARTICIPANT_TABLE, null);
        String[] participantsNames = new String[cursorParticipants.getCount()];

        int i = 0;
        while (cursorParticipants.moveToNext()){
            if (cursorParticipants.getString(1).contains(clubName)) {
                participantsNames[i] = cursorParticipants.getString(0);
                i++;
            }
        }
        cursorParticipants.close();
        return participantsNames;
    }
    public String[] getAllEvents(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorEvents = db.rawQuery("SELECT "+ EVENT_TYPE + " FROM " + EVENTS_TABLE, null);
        String[] eventNames = new String[cursorEvents.getCount()];

        int i = 0;
        while (cursorEvents.moveToNext()){
            eventNames[i] = cursorEvents.getString(0); //only one column selected
            i++;
        }
        cursorEvents.close();
        return eventNames;
    }

    @SuppressLint("Range")
    public String[] getEventInfo(String eventType){
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT " + EVENT_AGE + " FROM " + EVENTS_TABLE + " WHERE EVENT_TYPE = '" + eventType +"'", null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + EVENTS_TABLE + " WHERE EVENT_TYPE = '" + eventType + "'", null); // get all col info for specific event type
        String[] eventInfo;

        if (cursor.moveToFirst()) {
            String[] columnNames = cursor.getColumnNames();

            eventInfo = new String[columnNames.length];

            // Get col values
            for (int i = 0; i < columnNames.length; i++) {
                eventInfo[i] = cursor.getString(cursor.getColumnIndex(columnNames[i]));
                Log.d("eventInfo:", columnNames[i] + ": " + eventInfo[i]);
            }
        } else {
            Log.d("getEventInfo", "No rows found for eventType: " + eventType);
            eventInfo = new String[0];
        }

        cursor.close();
        return eventInfo;
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

    public void updateEvent(String prevEventType, String eventType, String eventAge, String eventPace, String eventLevel, String eventLocation, String eventTime, String eventDetails){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("DB", "Event updated");

        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_TYPE + " = '" + eventType + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_AGE + " = '" + eventAge + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_PACE + " = '" + eventPace + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_LEVEL + " = '" + eventLevel + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_LOCATION + " = '" + eventLocation + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_TIME + " = '" + eventTime + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
        db.execSQL("UPDATE "+ EVENTS_TABLE + " SET " + EVENT_DETAILS + " = '" + eventDetails + "' WHERE " + EVENT_TYPE + " = '" + prevEventType + "'");
    }
    public void completeClubAccount(String clubName, String link, String contactName, String phone){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("DB", "Club updated");

        db.execSQL("UPDATE "+ CLUB_TABLE + " SET " + CLUB_LINK + " = '" + link  + "' WHERE " + CLUB_NAME + " = '" + clubName + "'");
        db.execSQL("UPDATE "+ CLUB_TABLE + " SET " + CLUB_CONTACT + " = '" + contactName  + "' WHERE " + CLUB_NAME + " = '" + clubName + "'");
        db.execSQL("UPDATE "+ CLUB_TABLE + " SET " + CLUB_PHONE + " = '" + phone  + "' WHERE " + CLUB_NAME + " = '" + clubName + "'");
    }

    public void addAwardToParticipant(String username, String award) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Get current awards
        Cursor cursor = db.rawQuery("SELECT " + PARTICIPANT_AWARDS + " FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME + " = '" + username + "'", null);
        String awards = "";

        if (cursor.moveToFirst()) {
            awards = cursor.getString(0);
        }

        cursor.close();
        cv.put(PARTICIPANT_CLUBS, awards + ("," + award));
        db.update(PARTICIPANT_TABLE, cv, PARTICIPANT_USERNAME + "=?", new String[]{username});

    }
    public void addClubToParticipant(String username, String clubName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT " + PARTICIPANT_CLUBS + " FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME + " = '" + username + "'", null);
        String clubs = "";

        if (cursor.moveToFirst()) {
            clubs = cursor.getString(0);
        }

        cursor.close();
        cv.put(PARTICIPANT_CLUBS, clubs + ("," + clubName));
        db.update(PARTICIPANT_TABLE, cv, PARTICIPANT_USERNAME + "=?", new String[]{username});
    }

    public void removeClubFromParticipant(String username, String clubName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Get current awards
        Cursor cursor = db.rawQuery("SELECT " + PARTICIPANT_CLUBS + " FROM " + PARTICIPANT_TABLE + " WHERE " + PARTICIPANT_USERNAME + " = '" + username + "'", null);
        String events = "";

        if (cursor.moveToFirst()) {
            events = cursor.getString(0);
        }

        cursor.close();
        cv.put(PARTICIPANT_CLUBS, events.replace(clubName + ",", ""));
        db.update(PARTICIPANT_TABLE, cv, PARTICIPANT_USERNAME + "=?", new String[]{username});
    }

}
