package com.interview.fyk.feedyourknowledge.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abk on 28/01/2018.
 */

public final class DateUtils {

    public static final String TAG = DateUtils.class.getSimpleName();

    private DateUtils() {

    }

    public static String getFormattedTimeAgo(String pubDate) {
        Date date = getDate(pubDate);
        if (date == null)
            return "";
        String timeAgo = android.text.format.DateUtils
                .getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(), android.text.format.DateUtils.DAY_IN_MILLIS)
                .toString();
        return timeAgo;
    }

    private static Date getDate(String pubDate) {
        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        Date date;
        try {
            date = formatter.parse(pubDate);
        } catch (ParseException e) {
            date = null;
            Log.e(TAG, e.getMessage());
        }
        return date;
    }
}