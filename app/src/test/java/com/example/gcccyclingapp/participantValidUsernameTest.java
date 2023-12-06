package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class participantValidUsernameTest {

    public participantValidUsernameTest () {

    }
    @Test
    public void validateValidParticpantName() {
        assertFalse(Validate.isNotValidName("bob"));

    }
}

