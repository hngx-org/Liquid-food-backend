package org.hngxfreelunch.LiquidApplicationApi.exceptions;

public class LunchNotFoundException extends RuntimeException {

    public LunchNotFoundException() {
        this("Lunch Not Found!");
    }

    public LunchNotFoundException(String message) {
        super(message);
    }
}
