package com.nitin.massiveTurtle.utils;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {
    private static final FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

    /**
     * Convert a long time stamp (in milliseconds) to a human-readable format
     * @param timestamp
     * @return
     */
    public static String convertToDate(final Long timestamp) {
        return fastDateFormat.format(timestamp);
    }
}
