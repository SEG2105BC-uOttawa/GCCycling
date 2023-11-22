package com.example.gcccyclingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

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


    public DBClubs(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
        String createEventTableStatement = "CREATE TABLE IF NOT EXISTS " + clubName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
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

        cv.put(EVENT_TYPE, type);
        cv.put(EVENT_AGE, age);
        cv.put(EVENT_PACE, pace);
        cv.put(EVENT_LEVEL, level);
        cv.put(EVENT_LOCATION, location);
        cv.put(EVENT_TIME, time);
        cv.put(EVENT_DETAILS, details);

        addClub(clubName);
        db.insert(clubName, null, cv);
    }

    public boolean findEvent(String clubName, String eventName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + clubName + " WHERE " + EVENT_NAME + " =\"" + eventName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public void deleteEvent(String clubName, String event) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * FROM " + clubName + " WHERE " +
                EVENT_NAME + " = \"" + event + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(clubName)

        }
        db.delete(clubName, clubName, null);
        db.close();
        Log.d("Event", event + " from " + clubName + " deleted");
    }


}
