package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto<T> {

    private String message;
    private Integer statusCode;
    private T data;

    public ApiResponseDto(T data, String message, Integer statusCode){
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }
}
