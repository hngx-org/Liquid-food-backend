package org.hngxfreelunch.LiquidApplicationApi.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends FreeLunchException {
    public InvalidCredentials(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
