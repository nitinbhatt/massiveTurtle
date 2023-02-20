package com.nitin.massiveTurtle.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {

    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Convert a long time stamp (in milliseconds) to a human-readable format
     * @param timestamp
     * @return
     */
    public static String convertToDate(final Long timestamp) {
        return dateFormat.format(timestamp);
    }
}
