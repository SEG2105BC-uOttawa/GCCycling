package com.example.gcccyclingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBAdmin extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "admin.db";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String ADMIN_CLUBS_COLUMN = "ADMIN_CLUBS"; // column listing clubs admin has



    public DBAdmin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        String createTableStatement = "CREATE TABLE " +ADMIN_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_CLUBS_COLUMN +" TEXT)"; // create admin table that has list of clubs
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes

    }

    public void insert(String clubName){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions
        ContentValues cv = new ContentValues();

        cv.put(ADMIN_CLUBS_COLUMN, clubName); // inserts data into club column
        db.insert(ADMIN_TABLE, null, cv);

        Log.d("Data Insert", "Data inserted");
    }

    public void delete(String clubName){ // change to work with Club class
        SQLiteDatabase db = this.getWritableDatabase(); // for insert actions

        db.delete(ADMIN_TABLE, null, null);
        db.execSQL("DELETE FROM " + ADMIN_TABLE + " WHERE " + ADMIN_CLUBS_COLUMN + " = " + clubName);

        Log.d("Data Deleted", "Data Deleted");
    }

}
