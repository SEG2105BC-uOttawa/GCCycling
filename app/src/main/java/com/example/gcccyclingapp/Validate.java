package com.example.gcccyclingapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    // Note: Because of the way the regex works, the functions also return false
    // if the given string is empty
    private static final String PHONE_NUMBER_REGEX = ".[0-9]+..[0-9]+-[0-9]+";
    private static final String LINK_REGEX = "[a-zA-Z][a-zA-Z0-9+.-:]*";
    private static final String USERNAME_REGEX = "[a-zA-Z0-9-_]+";
    private static final String NAME_REGEX = "[a-zA-Z0-9-_ ]+";
    private static final String TIME_REGEX = "[a-zA-Z0-9:]+";
    private static final String NUMERIC_REGEX = "[a-zA-Z0-9]+";
    private static final String ALPHA_NUMERIC_REGEX = "[a-zA-Z0-9]+";

    public static boolean isNotValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }

    public static boolean isNotValidLink(String link) {
        Pattern pattern = Pattern.compile(LINK_REGEX);
        Matcher matcher = pattern.matcher(link);
        return !matcher.matches();
    }

    public static boolean isNotValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return !matcher.matches();
    }

    public static boolean isNotValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return !matcher.matches();
    }

    public static boolean isNotValidTime(String time) {
        Pattern pattern = Pattern.compile(TIME_REGEX);
        Matcher matcher = pattern.matcher(time);
        return !matcher.matches();
    }
    public static boolean isNotNumeric(String string) {
        Pattern pattern = Pattern.compile(NUMERIC_REGEX);
        Matcher matcher = pattern.matcher(string);
        return !matcher.matches();
    }
    public static boolean isNotAlphaNumeric(String string) {
        Pattern pattern = Pattern.compile(ALPHA_NUMERIC_REGEX);
        Matcher matcher = pattern.matcher(string);
        return !matcher.matches();
    }
}
