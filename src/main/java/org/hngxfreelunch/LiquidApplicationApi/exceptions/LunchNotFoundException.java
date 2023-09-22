package org.hngxfreelunch.LiquidApplicationApi.exceptions;

import org.springframework.http.HttpStatus;

public class LunchNotFoundException extends FreeLunchException {

    public LunchNotFoundException() {
        this("Lunch Not Found!");
    }

    public LunchNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
