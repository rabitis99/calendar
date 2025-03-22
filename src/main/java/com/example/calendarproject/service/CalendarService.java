package com.example.calendarproject.service;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import java.util.List;

public interface CalendarService {

    /**
     * 일정 생성
     * @param calendarRequestDto 일정 생성 요청 DTO
     * @return 생성된 일정 응답 DTO
     */
    CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto);

    /**
     * 모든 일정 조회
     * @return 일정 응답 DTO 목록
     */
    List<CalendarResponseDto> findAllCalendars();

    /**
     * 일정 수정
     *
     * @param id
     * @param calendarRequestDto 일정 수정 요청 DTO
     * @return 수정된 일정 응답 DTO
     */
    CalendarResponseDto updateCalendar(int id, CalendarRequestDto calendarRequestDto);

    /**
     * 일정 삭제
     * @param id 삭제할 일정의 ID
     */
    void deleteCalendar(int id,CalendarRequestDto calendarRequestDto);
}
