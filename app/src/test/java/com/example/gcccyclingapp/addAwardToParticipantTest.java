package com.example.gcccyclingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)


public class addAwardToParticipantTest {

    public addAwardToParticipantTest() {
    }

    @Mock
    Context mMockContext;
    @Mock
    SQLiteDatabase db;
    @Mock
    SQLiteOpenHelper mockSQLiteOpenHelper;

    @Before
    public void setup() {
        when(mockSQLiteOpenHelper.getWritableDatabase()).thenReturn(db);
    }
    @Test
    public void addAwardToParticipantTest() {

        DBAdmin dbAdmin = new DBAdmin(mMockContext, mockSQLiteOpenHelper);
//        db = dbAdmin.getWritableDatabase();

        assertTrue(dbAdmin.addAwardToParticipant("p", "award")); // works when running app
                                                                                // for some reason unit test wont work

    }
}

