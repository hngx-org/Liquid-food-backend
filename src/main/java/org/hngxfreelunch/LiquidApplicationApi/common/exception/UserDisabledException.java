package org.hngxfreelunch.LiquidApplicationApi.common.exception;

public class UserDisabledException extends RuntimeException{
    public UserDisabledException(String message) {
        super(message);
    }
}
