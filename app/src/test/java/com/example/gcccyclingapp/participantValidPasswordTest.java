package com.example.gcccyclingapp;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class participantValidPasswordTest {

    public participantValidPasswordTest () {

    }
    @Test
    public void validateValidPassword() {
        assertFalse(Validate.isNotValidName("password123"));

    }
}

