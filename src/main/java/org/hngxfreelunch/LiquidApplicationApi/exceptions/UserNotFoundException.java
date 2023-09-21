package org.hngxfreelunch.LiquidApplicationApi.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends FreeLunchException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
