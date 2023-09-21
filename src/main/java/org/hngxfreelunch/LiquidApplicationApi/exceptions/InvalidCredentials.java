package org.hngxfreelunch.LiquidApplicationApi.exceptions;

public class InvalidCredentials extends RuntimeException{
    public InvalidCredentials(String message) {
        super(message);
    }
}
