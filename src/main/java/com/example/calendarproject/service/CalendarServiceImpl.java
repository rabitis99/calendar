package com.example.calendarproject.service;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.entity.Calendar;
import com.example.calendarproject.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto) {
        Calendar calendar = new Calendar(calendarRequestDto);
        return calendarRepository.createCalendar(calendar);
    }

    @Override
    public List<CalendarResponseDto> findAllCalendars() {
        return calendarRepository.findAllCalendars();
    }

    @Override
    public CalendarResponseDto updateCalendar(int id, CalendarRequestDto calendarRequestDto) {
        boolean updated = calendarRepository.updateCalendar(calendarRequestDto);
        return updated ? calendarRepository.findCalendarByName(calendarRequestDto.getName()).orElse(null) : null;
    }

    @Override
    public void deleteCalendar(int id,CalendarRequestDto calendarRequestDto) {
        calendarRepository.deleteCalendar(id,calendarRequestDto);
    }
}