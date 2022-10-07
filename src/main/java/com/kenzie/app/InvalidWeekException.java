package com.kenzie.app;

public class InvalidWeekException extends IllegalArgumentException{
    public InvalidWeekException (String message) {
        super(message);
    }
}
