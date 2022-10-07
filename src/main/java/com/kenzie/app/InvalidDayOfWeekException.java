package com.kenzie.app;

public class InvalidDayOfWeekException extends IllegalArgumentException {
    public InvalidDayOfWeekException(String message) {
        super(message);
    }
}
