package org.georg.web.impl.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    static final Locale RU_LOCALE = new Locale("ru");
    static final DateFormatSymbols RU_SYMBOLS = new DateFormatSymbols(RU_LOCALE);
    static final String[] RU_MONTHS = {"января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};

    static {
        RU_SYMBOLS.setMonths(RU_MONTHS);
    }

    public static SimpleDateFormat formatterId = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat formatterText = new SimpleDateFormat("dd MMMM yyyy", RU_SYMBOLS);

    /**
     * Converts Date to String.
     *
     * @param date object to convert.
     * @return String in format dd MMMM yyyy.
     */
    public static String toTextString(Date date) {
        if (date != null) {
            return formatterText.format(date);
        }
        return null;
    }

    /**
     * Converts Date to String.
     *
     * @param date object to convert.
     * @return String in format dd MMMM yyyy.
     */
    public static String toIdString(Date date) {
        if (date != null) {
            return formatterId.format(date);
        }
        return null;
    }

    /**
     * Parse String to Date object.
     *
     * @param date String in format yyyy-MM-dd HH:mm:ss.
     * @return Date object or {@code null} if wrong format.
     */
    public static Date dateIdFromString(String date) {
        try {
            return formatterId.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
