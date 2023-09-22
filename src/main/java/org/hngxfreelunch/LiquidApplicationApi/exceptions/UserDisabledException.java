package org.hngxfreelunch.LiquidApplicationApi.exceptions;

import org.springframework.http.HttpStatus;

public class UserDisabledException extends FreeLunchException {
    public UserDisabledException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
