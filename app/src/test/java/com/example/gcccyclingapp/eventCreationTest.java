package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class eventCreationTest {

    public eventCreationTest() {

    }
    @Test
    public void createEventTest() {
        Event event = new Event("a", "a", "a", "a","a","a","a","a");
        assertEquals(event.getName(), "a");
    }
}

