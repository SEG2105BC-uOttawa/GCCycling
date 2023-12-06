package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class eventTypeSetTest {

    public eventTypeSetTest() {

    }
    @Test
    public void setTest() {
        EventType eventTYpe = new EventType("name", "type",3, 2.2, 23, "ottawa", "time","details" );
        eventTYpe.setName("newname");
        assertEquals(eventTYpe.getName(), "newname");
    }
}

