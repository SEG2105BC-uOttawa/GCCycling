package com.example.gcccyclingapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper{

    public static final String COLUMN_ID = "ID";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String COLUMN_ADMIN_USER = "ADMIN_USER";
    public static final String COLUMN_ADMIN_PWD = "ADMIN_PWD";


    public DBHandler(@Nullable Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // this is called when the database is first accessed
        String createTableStatement = "CREATE TABLE " + ADMIN_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ADMIN_USER + " TEXT, " + COLUMN_ADMIN_PWD + " TEXT)"; // statement for creating a table

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) { // called if database version number changes

    }

//    addOne()

}
