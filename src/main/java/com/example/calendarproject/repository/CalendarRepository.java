package com.example.calendarproject.repository;

import com.example.calendarproject.entity.CalendarEntity;
import java.util.List;
import java.util.Optional;

public interface CalendarRepository {

    CalendarEntity createCalendar(CalendarEntity calendar);

    List<CalendarEntity> findAllCalendars();

    Optional<CalendarEntity> findCalendarByName(String name);

    boolean updateCalendar(CalendarEntity calendar, String email);

    String deleteCalendar(long id, long userId);

    List<Long> checkUserId();

    List<Long> checkId();

    List<CalendarEntity> findCalendarById(long id);

    List<CalendarEntity> findCalendarsByUserId(long userId);
}
