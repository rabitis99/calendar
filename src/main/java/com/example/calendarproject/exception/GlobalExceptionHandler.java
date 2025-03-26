package com.example.calendarproject.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.example.calendarproject.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDto> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(HttpStatus.valueOf(ex.getErrorCode().getStatus()))
                .body(new ErrorDto(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()));
    }

//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<ErrorDto> handleServerException(Exception ex) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage()));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorDto> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
