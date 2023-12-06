package com.example.gcccyclingapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBClubs extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "clubs.db";
    public static final String EVENT_NAME = "EVENT_NAME";
    public static final String EVENT_TYPE = "EVENT_TYPE";
    public static final String EVENT_DIFFICULTY = "EVENT_DIFFICULTY";
    public static final String EVENT_FEE = "EVENT_FEE";
    public static final String EVENT_PARTICIPANT_LIMIT = "EVENT_PARTICIPANT_LIMIT";
    public static final String EVENT_DATE = "EVENT_DATE";
    public static final String EVENT_ROUTE = "EVENT_ROUTE";
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
        String createEventTableStatement = "CREATE TABLE IF NOT EXISTS '" + this.clubName + "' (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EVENT_NAME +" TEXT, "
                + EVENT_TYPE +" TEXT, "
                + EVENT_DIFFICULTY + " TEXT, "
                + EVENT_FEE + " TEXT, "
                + EVENT_PARTICIPANT_LIMIT + " TEXT, "
                + EVENT_DATE + " TEXT, "
                + EVENT_ROUTE + " TEXT)";

        db.execSQL(createEventTableStatement);
    }

    // inserts event into its corresponding club table
    public void insertEvent(String clubName, String name, String type, String difficulty, String fee, String participantLimit, String date, String route) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        this.clubName = clubName;

        cv.put(EVENT_NAME, name);
        cv.put(EVENT_TYPE, type);
        cv.put(EVENT_DIFFICULTY, difficulty);
        cv.put(EVENT_FEE, fee);
        cv.put(EVENT_PARTICIPANT_LIMIT, participantLimit);
        cv.put(EVENT_DATE, date);
        cv.put(EVENT_ROUTE, route);

        db.insert(this.clubName, null, cv);
    }

//    public boolean findEvent(String clubName, String eventName) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        this.clubName = clubName;
//        String query = "Select * FROM " + this.clubName + " WHERE " + EVENT_NAME + " =\"" + eventName + "\"";
//        db.close();
//
//        if (query != null) {
//            return true;
//        }
//        return false;
//    }

    public void deleteEvent(String clubName, String eventName) {
        SQLiteDatabase db = getWritableDatabase();
        this.clubName = clubName;

        String whereClause = EVENT_NAME + " = ?";
        String[] whereArgs = new String[]{eventName};

        db.delete(this.clubName, whereClause, whereArgs);
        db.close();
    }

    public void updateEvent(String clubName, String oldName, String name, String type, String difficulty, String fee, String participantLimit, String date, String route){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT rowid FROM " + clubName + " WHERE EVENT_NAME = '" + oldName + "'", null); // get all col info for specific event type
        if (cursor.moveToNext()) {
            String rowId = cursor.getString(0);
            Log.d("DB", "Event updated");

            Log.d("new event name", name);

            ContentValues cv = new ContentValues();
            cv.put(EVENT_NAME, name);
            cv.put(EVENT_TYPE, type);
            cv.put(EVENT_DIFFICULTY, difficulty);
            cv.put(EVENT_FEE, fee);
            cv.put(EVENT_PARTICIPANT_LIMIT, participantLimit);
            cv.put(EVENT_DATE, date);
            cv.put(EVENT_ROUTE, route);

            db.update(clubName, cv, "rowid=?", new String[]{rowId});
        }
        cursor.close();
    }

    public void deleteClub(String clubName) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + clubName + ";";
        db.execSQL(query);
        db.close();
    }

    @SuppressLint("Range")
    public String[] getEventInfo(String clubName, String eventName){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("DBClubs", "clubName: " + clubName);
        Log.d("DBClubs", "eventName: " + eventName);
//        Cursor cursor = db.rawQuery("SELECT " + EVENT_AGE + " FROM " + EVENTS_TABLE + " WHERE EVENT_TYPE = '" + eventType +"'", null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + clubName + " WHERE EVENT_NAME = '" + eventName + "'", null); // get all col info for specific event type
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
            Log.d("getEventInfo", "No rows found for event: " + eventName);
            eventInfo = new String[0];
        }

        cursor.close();
        return eventInfo;
    }

    public String[] getAllEvents(String clubName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorClubs;

        while (true) {
            try {
                cursorClubs = db.rawQuery("SELECT " + EVENT_NAME + " FROM " + clubName, null);
                break;
            } catch (SQLException e) {
                this.addClub(clubName);
            }
        }

        String[] eventNames = new String[cursorClubs.getCount()];

        int i = 0;
        while (cursorClubs.moveToNext()){
            eventNames[i] = cursorClubs.getString(0); //only one column selected
            i++;
        }
        cursorClubs.close();
        return eventNames;
    }

    public Event[] getAllEventsObject(String clubName){
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
            String limit = cursor.getString(5);
            String date = cursor.getString(6);
            String route = cursor.getString(7);

//            event = new Event(type, difficulty, fee, route, limit);
//            events[i] = event;
            event = new Event(name, type, difficulty, fee, route, limit, date, clubName);
            events[i] = event;
            i++;
        }
        cursor.close();
        return events;
    }

    public String[] getAllEventsGeneral(DBAdmin dbA){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorClubs = null;

        String[] clubs = dbA.getAllClubs();

        for (int i = 0; i < clubs.length; i++) {
            while (true) {
                try {
                    cursorClubs = db.rawQuery("SELECT " + EVENT_NAME + " FROM " + clubs[i], null);
                    break;
                } catch (SQLException e) {
                    System.out.println("Error in getAllEventsGeneral: "+e);
                }
            }
        }

        if (cursorClubs == null) { // check if null
            Log.d("getAllEventsGeneral", "No events");
            return null;
        }

        String[] eventNames = new String[cursorClubs.getCount()];

        int i = 0;
        while (cursorClubs.moveToNext()){
            eventNames[i] = cursorClubs.getString(0); //only one column selected
            i++;
        }
        cursorClubs.close();
        return eventNames;
    }


    // search by criteria methods
    public String[] getAllEventsByLocation(String[] eventsTypes, DBAdmin dbA){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorClubs = null;
        List<String> eventNamesList = new ArrayList<>();

        String[] clubs = dbA.getAllClubs();

        for (int i = 0; i < clubs.length; i++) {
            for (int j = 0; j < eventsTypes.length; j++) {
                System.out.println("Checking "+eventsTypes[j]+" in "+clubs[i]);
                try {
                    cursorClubs = db.rawQuery("SELECT " + EVENT_NAME + " FROM " + clubs[i] + " WHERE " + EVENT_TYPE + " = '" + eventsTypes[j]+"'", null);
                    while (cursorClubs.moveToNext()){
                        eventNamesList.add(cursorClubs.getString(0));
                    }
                } catch (SQLException e) {
                    System.out.println("Error in getAllEventsByLocation: "+e);
                }
            }
        }

        cursorClubs.close();
        return eventNamesList.toArray(new String[0]);
    }

    public String[] getAllEventsByDate(String date, DBAdmin dbA){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorClubs = null;

        String[] clubs = dbA.getAllClubs();

        for (int i = 0; i < clubs.length; i++) {
            while (true) {
                try {
                    cursorClubs = db.rawQuery("SELECT " + EVENT_NAME + " FROM " + clubs[i] + " WHERE " + EVENT_DATE + " = '" + date+"'", null);
                    break;
                } catch (SQLException e) {
                    System.out.println("Error in getAllEventsByLocation: "+e);
                }
            }
        }

        if (cursorClubs == null) { // check if null
            Log.d("getAllEventsByDate", "No events with that date");
            return null;
        }

        String[] eventNames = new String[cursorClubs.getCount()];

        int i = 0;
        while (cursorClubs.moveToNext()){
            eventNames[i] = cursorClubs.getString(0); //only one column selected
            i++;
        }
        cursorClubs.close();
        return eventNames;
    }

    public String[] getAllEventsByType(String type, DBAdmin dbA){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorClubs = null;

        String[] clubs = dbA.getAllClubs();

        for (int i = 0; i < clubs.length; i++) {
            while (true) {
                try {
                    cursorClubs = db.rawQuery("SELECT " + EVENT_NAME + " FROM " + clubs[i] + " WHERE " + EVENT_TYPE + " = '" + type+"'", null);
                    break;
                } catch (SQLException e) {
                    System.out.println("Error in getAllEventsByType: "+e);
                }
            }
        }

        if (cursorClubs == null) { // check if null
            Log.d("getAllEventsByType", "No events with that type");
            return null;
        }

        String[] eventNames = new String[cursorClubs.getCount()];

        int i = 0;
        while (cursorClubs.moveToNext()){
            eventNames[i] = cursorClubs.getString(0); //only one column selected
            i++;
        }
        cursorClubs.close();
        return eventNames;
    }




}
