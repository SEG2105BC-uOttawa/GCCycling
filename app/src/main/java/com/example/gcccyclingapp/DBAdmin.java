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
    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String ADMIN_CLUB_NAME = "ADMIN_CLUB_NAME"; // column listing clubs admin has
    public static final String ADMIN_CLUB_USERNAME = "ADMIN_CLUB_USERNAME"; // column listing clubs admin has
    public static final String ADMIN_CLUB_PASSWORD = "ADMIN_CLUB_PASSWORD"; // column listing clubs admin has



    public DBAdmin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        String createTableStatement = "CREATE TABLE " +ADMIN_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_CLUB_NAME +" TEXT, " + ADMIN_CLUB_USERNAME +" TEXT, " + ADMIN_CLUB_PASSWORD + " TEXT)"; // create admin table that has list of clubs
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes

    }

    public void insert(String clubName, String clubUser, String clubPWD){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        ContentValues cv = new ContentValues();

        cv.put(ADMIN_CLUB_NAME, clubName); // inserts data into club column
        cv.put(ADMIN_CLUB_USERNAME, clubUser); // inserts data into club column
        cv.put(ADMIN_CLUB_PASSWORD, clubPWD); // inserts data into club column

        db.insert(ADMIN_TABLE, null, cv);
    }

    public void delete(String clubName){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions

        String queryString = "DELETE FROM " + ADMIN_TABLE + " WHERE " + ADMIN_CLUB_NAME + " = " + clubName;
        Cursor cursor = db.rawQuery(queryString, null);

        db.delete(ADMIN_TABLE, ADMIN_CLUB_NAME+" = "+clubName, null);
        db.execSQL("DELETE FROM " + ADMIN_TABLE + " WHERE " + ADMIN_CLUB_NAME + " = " + clubName);
    }

}
