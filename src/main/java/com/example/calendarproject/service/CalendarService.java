package com.example.calendarproject.service;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.entity.CalendarEntity;

import java.util.List;

public interface CalendarService {

    CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto);

    List<CalendarResponseDto> findAllCalendars();

    CalendarResponseDto updateCalendar(long id, CalendarRequestDto calendarRequestDto, String email);

    String deleteCalendar(long id,String email,long userId);

    List<CalendarResponseDto> findAllCalendarsById(long id);

    List<CalendarResponseDto> findCalendarsByUserId(long userId);
}
