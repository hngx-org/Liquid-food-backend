package org.hngxfreelunch.LiquidApplicationApi.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.ExceptionResponse;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.InvalidCredentials;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserDisabledException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationErrors(MethodArgumentNotValidException e){
        Map<String, String> invalidErrors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error ->{
                    String fieldError = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    invalidErrors.put(fieldError, message);
                });
        return new ResponseEntity<>(invalidErrors, HttpStatus.BAD_REQUEST);
    }
}
