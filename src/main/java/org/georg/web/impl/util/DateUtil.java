package org.georg.web.impl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Converts Date to String.
     *
     * @param date object to convert.
     * @return String in format yyyy-MM-dd HH:mm:ss.
     */
    public static String toString(Date date) {
        if (date != null) {
            return formatter.format(date);
        }
        return null;
    }

    /**
     * Parse String to Date object.
     *
     * @param date String in format yyyy-MM-dd HH:mm:ss.
     * @return Date object or {@code null} if wrong format.
     */
    public static Date dateFromString(String date) {
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
