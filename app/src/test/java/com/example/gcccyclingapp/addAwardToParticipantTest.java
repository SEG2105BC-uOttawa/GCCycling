package com.example.gcccyclingapp;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)


public class addAwardToParticipantTest {

    public addAwardToParticipantTest() {
    }

    @Mock
    Context mMockContext;
    @Test
    public void addAwardToParticipantTest() {
        DBAdmin dbAdmin = new DBAdmin(mMockContext);
        boolean result = dbAdmin.addAwardToParticipant("bob", "sam");
        assertEquals(result,true);

    }
}

