package org.hngxfreelunch.LiquidApplicationApi.data.dtos;

import lombok.Data;

@Data
public class GlobalResponse <T>{
    private String message;
    private Integer statusCode;
    private T data;

    public GlobalResponse(T data, String message, Integer statusCode){
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }
}
