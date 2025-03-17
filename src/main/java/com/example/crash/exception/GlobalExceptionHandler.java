package com.example.crash.exception;

import com.example.crash.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleClientErrorException(ClientErrorException e) {
        return new ResponseEntity<>(
                    new ErrorResponse(e.getStatus(),e.getMessage()),e.getStatus());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorMessages = e.getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).toList().toString();
        return  new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST,errorMessages),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return  new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage()),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        //500번대 오류 반환
        return ResponseEntity.internalServerError().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        //500번대 오류 반환
        return ResponseEntity.internalServerError().build();
    }
}
