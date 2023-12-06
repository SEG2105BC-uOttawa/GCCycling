package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class clubGetEventTest {

    public clubGetEventTest() {

    }
    @Test
    public void getEventTest() {
        Club club = new Club("bob", "bobby", "bob123", "partOfClub");
        assertEquals(club.getUsername(), "bobby");
    }
}

