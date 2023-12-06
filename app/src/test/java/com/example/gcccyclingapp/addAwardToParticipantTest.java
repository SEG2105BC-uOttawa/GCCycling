package com.example.gcccyclingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)


public class addAwardToParticipantTest {

    public addAwardToParticipantTest() {
    }

    @Mock
    Context mMockContext;
    @Mock
    SQLiteDatabase db;
    @Test
    public void addAwardToParticipantTest() {

        DBAdmin dbAdmin = new DBAdmin(mMockContext);
        db = dbAdmin.getWritableDatabase();

        assertTrue(dbAdmin.addAwardToParticipant("bob", "sam"));

    }
}

