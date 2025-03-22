package com.example.calendarproject.repository;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.entity.Calendar;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CalendarRepository {

    /**
     * 새로운 일정을 생성합니다.
     * @param calendar 일정 엔티티
     * @return 생성된 일정 응답 DTO
     */
    CalendarResponseDto createCalendar(Calendar calendar);

    /**
     * 모든 일정을 조회합니다.
     * @return 일정 응답 DTO 목록
     */
    List<CalendarResponseDto> findAllCalendars();

    /**
     * 이름을 기반으로 특정 일정을 조회합니다.
     * @param name 일정 이름
     * @return 조회된 일정 응답 DTO
     */
    Optional<CalendarResponseDto> findCalendarByName(String name);

    /**
     * 일정을 수정합니다.
     * @param calendarRequestDto 일정 수정 요청 DTO
     * @return 수정 성공 여부 (true: 성공, false: 실패)
     */
    boolean updateCalendar(CalendarRequestDto calendarRequestDto);

    /**
     * 일정을 삭제합니다.
     * @param calendarRequestDto 일정 삭제 요청 DTO
     * @return 삭제 성공 여부 (true: 성공, false: 실패)
     */
    boolean deleteCalendar(int id,CalendarRequestDto calendarRequestDto);
}
