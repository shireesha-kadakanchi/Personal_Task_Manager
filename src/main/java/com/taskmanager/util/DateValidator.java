package com.taskmanager.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import com.taskmanager.exception.InvalidDateException;

public class DateValidator {
    public static LocalDate parseDate(String dateStr) throws InvalidDateException {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format: " + dateStr);
        }
    }
}
