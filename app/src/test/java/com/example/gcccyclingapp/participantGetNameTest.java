package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class participantGetNameTest {

    public participantGetNameTest() {

    }
    @Test
    public void participantNameTest() {
        Participant participant = new Participant("bob", "username", "password", "role");
        assertEquals(participant.getName(), "bob");
    }
}

