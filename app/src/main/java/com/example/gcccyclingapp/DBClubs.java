package com.example.gcccyclingapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.database.Cursor;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBClubs extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "clubs.db";

//    public static final String CLUB = "club";
//    public static final String CLUB_NAME = "clubName";
//    public static final String CLUB_LINK = "clubLink";
//    public static final String CLUB_CONTACT = "clubContact";
//    public static final String CLUB_PHONE = "clubPhone";
//    public static final String CLUB_EVENT = "clubEvent";

//    EditText type = (EditText) findViewById(R.id.eventTypetxt);
//    EditText age = (EditText) findViewById(R.id.agetxt);
//    EditText pace = (EditText) findViewById(R.id.pacetxt);
//    EditText level = (EditText) findViewById(R.id.leveltxt);
//    EditText location = (EditText) findViewById(R.id.locationtxt);
//    EditText time = (EditText) findViewById(R.id.timetxt);
//    EditText details = (EditText) findViewById(R.id.detail

    public static final String EVENT_NAME = "eventName";
    public static final String EVENT_TYPE = "eventType";
    public static final String EVENT_AGE = "eventAge";
    public static final String EVENT_PACE = "eventPace";
    public static final String EVENT_LEVEL = "eventLevel";
    public static final String EVENT_LOCATION = "eventLocation";
    public static final String EVENT_TIME = "eventTime";
    public static final String EVENT_DETAILS = "eventDetails";

    public DBClubs(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // no tables will be created when the database is created
    }

    // each club will get its own table
    public void addClub(String clubName) {
        SQLiteDatabase db = getWritableDatabase();
        String createClubTable = "CREATE TABLE " + clubName +
                " ( INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EVENT_NAME + " TEXT )";
        db.execSQL(createClubTable);
        db.close();
        Log.d("Club table ", clubName + " created");
    }

//    public void addClub(String clubName){
//        SQLiteDatabase db = getWritableDatabase();
//        String createClubTable = "CREATE TABLE " + clubName +
//                " ( INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                EVENT_NAME +" TEXT, " +
//                EVENT_TYPE +" TEXT, " +
//                EVENT_AGE +" TEXT, " +
//                EVENT_PACE +" TEXT, " +
//                EVENT_LEVEL +" TEXT, " +
//                EVENT_LOCATION +" TEXT, " +
//                EVENT_TIME +" TEXT, " +
//                EVENT_DETAILS +" TEXT)";
//        db.execSQL(createClubTable);
//        db.close();
//        Log.d("Club table ", clubName+" created");
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
//        onCreate(db);
        //empty
    }


    // could possibly be simplified with an event class
    public void addEvent(String clubName, Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getName());
        db.insert(clubName, null, values);
        db.close();
    }

    public boolean findEvent(String clubName, String eventName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + clubName + " WHERE " + EVENT_NAME + " =\"" + eventName + "\"";
        db.close();

        if (query != null) {
            return true;
        }

        return false;
    }

    public void deleteEvent(String clubName, String event) {
        Log.d("Event to be deleted", event);
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * FROM " + clubName;
        db.delete(clubName, clubName, null);
        db.close();
        Log.d("Event", event + " from " + clubName + " deleted");
    }

//    public void completeClubAccount(String clubName, String link, String contactName, String phone) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Log.d("DB", "Club updated");
//
//        db.execSQL("UPDATE " + clubName + " SET " + CLUB_LINK + " = '" + link + "'");
//        db.execSQL("UPDATE " + clubName + " SET " + CLUB_CONTACT + " = '" + contactName + "'");
//        db.execSQL("UPDATE " + clubName + " SET " + CLUB_PHONE + " = '" + phone + "'");
//    }

}
