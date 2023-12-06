package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class eventTypeGetTest {

    public eventTypeGetTest() {

    }
    @Test
    public void getTypeTest() {
        Event event = new Event("a", "type", "a", "a","a","a","a","a");
        event.getType();
        assertEquals(event.getType(), "type");
    }
}

