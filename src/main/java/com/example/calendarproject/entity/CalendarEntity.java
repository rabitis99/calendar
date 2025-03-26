package com.example.calendarproject.entity;

import com.example.calendarproject.dto.CalendarRequestDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 일정 엔티티
 * 일정 데이터를 저장하는 핵심 클래스
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CalendarEntity {
    private long id;//primary key
    private String task;//할일
    private String password;//비밀번호
    private String name;//작성자명
    private String updatedAt;//작성일
    private String createdAt;//수정일
    private long userId;//field key

    /**
     * CalendarRequestDto에서 입력된 정보 넣기
     *
     */
    public CalendarEntity(CalendarRequestDto requestDto) {
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.task = requestDto.getTask();
        this.userId=requestDto.getUserId();
    }

    public CalendarEntity(long id, long userId) {
        this.id=id;
        this.userId=userId;
    }
}
