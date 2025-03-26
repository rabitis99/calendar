package com.example.calendarproject.dto;

import com.example.calendarproject.entity.CalendarEntity;
import lombok.Getter;

import java.util.List;

/**
 * 일정 응답 DTO
 * 클라이언트에게 반환되는 일정 정보
 */
@Getter
public class CalendarResponseDto {
    private final long id;//primary key
    private final String task;//할일
    private final String name;//작성자명
    private final String updatedAt;//작성일
    private final String createdAt;//수정일

    /**
     * 엔티티를 기반으로 DTO 생성
     */
    public CalendarResponseDto(CalendarEntity calendar) {
        this.id = calendar.getId();
        this.name = calendar.getName();
        this.updatedAt= calendar.getUpdatedAt();
        this.createdAt = calendar.getCreatedAt();
        this.task = calendar.getTask();
    }

}
