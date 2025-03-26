package com.example.calendarproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {
    int stauts;
    String getErrorCode;

}
