package com.example.gcccyclingapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DBClubs extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "clubs.db";
    public static final String EVENT_NAME = "EVENT_NAME";
    public static final String EVENT_TYPE = "EVENT_TYPE";
    public static final String EVENT_AGE = "EVENT_AGE";

    public static final String EVENT_PACE = "EVENT_PACE";

    public static final String EVENT_LEVEL = "EVENT_LEVEL";
    public static final String EVENT_LOCATION = "EVENT_LOCATION";

    public static final String EVENT_TIME = "EVENT_TIME";
    public static final String EVENT_DETAILS = "EVENT_DETAILS";

    public static final String EVENT_DIFFICULTY = "EVENT_DIFFICULTY";

    public static final String EVENT_ROUTE = "EVENT_ROUTE";

    public static final String EVENT_FEE = "EVENT_FEE";
    public static final String EVENT_PARTICIPANT_LIMIT = "EVENT_PARTICIPANT_LIMIT";



    public static String clubName;


    public DBClubs(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        clubName = "";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // start will empty database. No tables
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addClub(String clubName) {
        SQLiteDatabase db = getWritableDatabase();
        this.clubName = clubName;
        String createEventTableStatement = "CREATE TABLE IF NOT EXISTS " + this.clubName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EVENT_TYPE +" TEXT, "
                + EVENT_AGE +" TEXT, "
                + EVENT_PACE + " TEXT, "
                + EVENT_LEVEL + " TEXT, "
                + EVENT_LOCATION + " TEXT, "
                + EVENT_TIME + " TEXT, "
                + EVENT_DETAILS + " TEXT)";

        db.execSQL(createEventTableStatement);
    }
    public void insertEvent(String clubName, String type, String age, String pace, String level, String location, String time, String details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        this.clubName = clubName;

        cv.put(EVENT_TYPE, type);
        cv.put(EVENT_AGE, age);
        cv.put(EVENT_PACE, pace);
        cv.put(EVENT_LEVEL, level);
        cv.put(EVENT_LOCATION, location);
        cv.put(EVENT_TIME, time);
        cv.put(EVENT_DETAILS, details);

        addClub(this.clubName);
        db.insert(this.clubName, null, cv);
    }

    public void insertEvent(String clubName, String type, String difficulty, String route, String fee, String participantLimit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        this.clubName = clubName;

        cv.put(EVENT_TYPE, type);
        cv.put(EVENT_DIFFICULTY, difficulty);
        cv.put(EVENT_ROUTE, route);
        cv.put(EVENT_FEE, fee);
        cv.put(EVENT_PARTICIPANT_LIMIT, participantLimit);

        addClub(this.clubName);
        db.insert(this.clubName, null, cv);
    }

    public boolean findEvent(String clubName, String eventName) {
        SQLiteDatabase db = this.getReadableDatabase();
        this.clubName = clubName;
        String query = "Select * FROM " + this.clubName + " WHERE " + EVENT_NAME + " =\"" + eventName + "\"";
        db.close();

        if (query != null) {
            return true;
        }

        return false;
    }

    public boolean findEventType(String clubName, String eventName) {
        SQLiteDatabase db = this.getReadableDatabase();
        this.clubName = clubName;
        String query = "Select * FROM " + this.clubName + " WHERE " + EVENT_NAME + " =\"" + eventName + "\"";
        db.close();

        if (query != null) {
            return true;
        }

        return false;
    }

    public void deleteEvent(String clubName, String eventName) {
        SQLiteDatabase db = getWritableDatabase();
        this.clubName = clubName;
        String query = "Select * FROM " + this.clubName;
        db.delete(this.clubName, eventName, null);
        db.close();
    }

}
