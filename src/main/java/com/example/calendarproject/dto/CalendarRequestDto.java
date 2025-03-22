package com.example.calendarproject.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 일정 요청 DTO
 * 클라이언트에서 일정을 생성하거나 수정할 때 사용
 */
@Getter
@Setter
public class CalendarRequestDto {
    private String name;
    private String date;
    private String revisedDate;
    private String password;
    private String contents;
}

