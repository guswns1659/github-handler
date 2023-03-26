package com.titanic.jack.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeComparison {
    public static boolean isMoreThanThreeWeekdaysApart(LocalDateTime start, LocalDateTime end) {
        int countWeekdays = 0;
        LocalDateTime currentDateTime = start;

        while (currentDateTime.isBefore(end)) {
            int dayOfWeek = currentDateTime.getDayOfWeek().getValue();
            if (dayOfWeek >= 1 && dayOfWeek <= 5) { // Monday is 1, Friday is 5
                countWeekdays++;
                if (countWeekdays > 3) {
                    return true;
                }
            }
            currentDateTime = currentDateTime.plus(1, ChronoUnit.DAYS);
        }

        return false;
    }
}
