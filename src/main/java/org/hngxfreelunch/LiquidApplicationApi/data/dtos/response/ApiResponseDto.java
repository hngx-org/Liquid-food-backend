package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {

    private String message;
    private int statusCode;
    private T data;

    public ApiResponseDto(T data, String message, Integer statusCode){
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }
}
