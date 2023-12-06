package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class participantSetNameTest {

    public participantSetNameTest() {

    }
    @Test
    public void participantNameTest() {
        Participant participant = new Participant("bob", "username", "password", "role");
        participant.setName("sammy");
        assertEquals(participant.getName(), "sammy");
    }
}

