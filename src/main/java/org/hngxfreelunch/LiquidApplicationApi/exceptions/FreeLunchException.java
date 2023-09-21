package org.hngxfreelunch.LiquidApplicationApi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FreeLunchException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public FreeLunchException(){
        this("Error Processing Request!");
    }

    public FreeLunchException(String message){
        super(message);
    }

    public FreeLunchException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }

}
