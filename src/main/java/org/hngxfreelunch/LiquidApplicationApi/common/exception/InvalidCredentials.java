package org.hngxfreelunch.LiquidApplicationApi.common.exception;

public class InvalidCredentials extends RuntimeException{
    public InvalidCredentials(String message) {
        super(message);
    }
}
