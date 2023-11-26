package com.example.gcccyclingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DBClubs extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "clubs.db";
    public static final String EVENT_NAME = "EVENT_NAME";
    public static final String EVENT_TYPE = "EVENT_TYPE";
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

    // will be called everytime a new club is created
    public void addClub(String clubName) {
        SQLiteDatabase db = getWritableDatabase();
        this.clubName = clubName;
        String createEventTableStatement = "CREATE TABLE IF NOT EXISTS " + this.clubName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EVENT_NAME +" TEXT, "
                + EVENT_TYPE +" TEXT, "
                + EVENT_DIFFICULTY + " TEXT, "
                + EVENT_FEE + " TEXT, "
                + EVENT_ROUTE + " TEXT, "
                + EVENT_PARTICIPANT_LIMIT + " TEXT)";

        db.execSQL(createEventTableStatement);
    }

    // inserts event into its corresponding club table
    public void insertEvent(String clubName, String name, String type, String difficulty, String route, String fee, String participantLimit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        this.clubName = clubName;

        cv.put(EVENT_NAME, name);
        cv.put(EVENT_TYPE, type);
        cv.put(EVENT_DIFFICULTY, difficulty);
        cv.put(EVENT_ROUTE, route);
        cv.put(EVENT_FEE, fee);
        cv.put(EVENT_PARTICIPANT_LIMIT, participantLimit);

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

    public void deleteEvent(String clubName, String eventName) {
        SQLiteDatabase db = getWritableDatabase();
        this.clubName = clubName;
        db.delete(this.clubName, eventName, null);
        db.close();
    }

    public void deleteClub(String clubName) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + clubName + ";";
        db.execSQL(query);
        db.close();
    }

    public Event[] getAllEvents(String clubName) {
        String table = clubName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);

        Event[] events = new Event[cursor.getCount()];
        Event event;

        int i = 0;
        while (cursor.moveToNext()){
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String difficulty = cursor.getString(3);
            String fee = cursor.getString(4);
            String route = cursor.getString(5);
            String limit = cursor.getString(6);
            event = new Event(name, type, difficulty, fee, route, limit);
            events[i] = event;
            i++;
        }
        cursor.close();
        return events;
    }

}
