package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class getClubEventsTest {

    public getClubEventsTest() {

    }
    @Test
    public void gettingClubEvents() {
        Club club = new Club("bob", "bobby", "bob123", "partOfClub");
        club.setUsername("tommy");
        assertEquals(club.getUsername(), "tommy");
    }
}

