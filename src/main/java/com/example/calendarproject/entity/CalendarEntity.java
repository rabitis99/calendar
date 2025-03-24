package com.example.calendarproject.entity;

import com.example.calendarproject.dto.CalendarRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 일정 엔티티
 * 일정 데이터를 저장하는 핵심 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Calendar {
    private int id;
    private String revisedDate;
    private String name;
    private String date;
    private String password;
    private String contents;

    /**
     * DTO를 기반으로 Calendar 객체 생성
     */
    public Calendar(CalendarRequestDto requestDto) {
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

}
