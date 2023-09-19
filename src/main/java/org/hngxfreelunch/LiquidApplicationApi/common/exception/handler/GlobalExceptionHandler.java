package org.hngxfreelunch.LiquidApplicationApi.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.ExceptionResponse;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.UserDisabledException;
import org.hngxfreelunch.LiquidApplicationApi.common.exception.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.common.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException e,
                                                          HttpServletRequest request){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .time(DateUtils.convertDate(LocalDateTime.now()))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ExceptionResponse> userDisabled(UserDisabledException e,
                                                          HttpServletRequest request){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .time(DateUtils.convertDate(LocalDateTime.now()))
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ExceptionResponse> invalidCredentials(InvalidCredentials e,
                                                          HttpServletRequest request){
        ExceptionResponse ex = ExceptionResponse.builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .time(DateUtils.convertDate(LocalDateTime.now()))
                .statusCode(HttpStatus.CONFLICT.value())
                .build();
        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }
}
