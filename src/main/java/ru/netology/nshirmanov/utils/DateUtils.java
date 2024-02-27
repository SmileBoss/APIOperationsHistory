package ru.netology.nshirmanov.utils;

import java.time.LocalDate;

public class DateUtils {
    public static boolean localDateIsAfterOrEqual(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2) || date1.equals(date2);
    }

    public static boolean localDateIsBeforeOrEqual(LocalDate date1, LocalDate date2) {
        return date1.isBefore(date2) || date1.equals(date2);
    }
}
