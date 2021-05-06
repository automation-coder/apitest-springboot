package com.crypto.cryptoassignment.util;

import java.time.temporal.ChronoUnit;

public class CommonActions {

    public static long getTimeInMillis(int valueAsInteger,
                                       ChronoUnit timeUnit) {
        long timeInMiliSeconds = 0;
        switch ((timeUnit)) {
            case MINUTES: {
                timeInMiliSeconds = valueAsInteger * 60 * 1000;
                break;
            }
            case HOURS: {
                timeInMiliSeconds = valueAsInteger * 60 * 60 * 1000;
                break;
            }
            case DAYS: {
                timeInMiliSeconds = valueAsInteger * 24 * 60 * 1000;
                break;
            }
            case WEEKS: {
                timeInMiliSeconds = valueAsInteger * 7 * 24 * 60 * 1000;
                break;
            }
            case MONTHS: {
                timeInMiliSeconds = valueAsInteger * 30 * 24 * 60 * 1000;
                break;
            }
        }
        return timeInMiliSeconds;
    }
}
