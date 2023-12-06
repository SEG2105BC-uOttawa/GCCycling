package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class clubSetEventTest {

    public clubSetEventTest() {

    }
    @Test
    public void setEventTest() {
        Club club = new Club("bob", "bobby", "bob123", "partOfClub");
        club.setUsername("tommy");
        assertEquals(club.getUsername(), "tommy");
    }
}

