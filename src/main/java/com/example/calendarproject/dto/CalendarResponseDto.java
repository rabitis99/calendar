package com.example.calendarproject.dto;

import com.example.calendarproject.entity.Calendar;
import lombok.Getter;

/**
 * 일정 응답 DTO
 * 클라이언트에게 반환되는 일정 정보
 */
@Getter
public class CalendarResponseDto {
    private int id;
    private String name;
    private String date;
    private String revisedDate;
    private String contents;

    /**
     * 엔티티를 기반으로 DTO 생성
     */
    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.name = calendar.getName();
        this.date = calendar.getDate();
        this.revisedDate = calendar.getRevisedDate();
        this.contents = calendar.getContents();
    }

    /**
     * 특정 값들을 기반으로 DTO 생성
     */
    public CalendarResponseDto(int id, String name, String contents, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.contents = contents;
    }
}
