package com.example.velaassignment.utils;

import com.sun.org.apache.regexp.internal.RE;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateTimeUtils() {
    }

    public static String format(OffsetDateTime dateTime, String pattern) {
        if (pattern.equals("") || pattern == null) {
            pattern = DATE_TIME_PATTERN;
        }
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    public static String format(OffsetDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return dateTime.format(formatter);
    }

}
