package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class eventTypeNameTest {

    public eventTypeNameTest() {

    }
    @Test
    public void getTypeTest() {
        EventType eventTYpe = new EventType("name", "type",3, 2.2, 23, "ottawa", "time","details" );
        assertEquals(eventTYpe.getType(), "type");
    }
}

