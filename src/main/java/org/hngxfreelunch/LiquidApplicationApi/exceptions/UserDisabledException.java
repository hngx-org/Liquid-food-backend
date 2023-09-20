package org.hngxfreelunch.LiquidApplicationApi.exceptions;

public class UserDisabledException extends RuntimeException{
    public UserDisabledException(String message) {
        super(message);
    }
}
