package com.example.calendarproject.repository;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.entity.Calendar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CalendarRepositoryImpl implements CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalendarRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CalendarResponseDto createCalendar(Calendar calendar) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", calendar.getName());
        parameters.put("contents", calendar.getContents());
        parameters.put("date", calendar.getDate());
        parameters.put("password", calendar.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        calendar.setId(key.intValue());
        return new CalendarResponseDto(calendar);
    }

    @Override
    public List<CalendarResponseDto> findAllCalendars() {
        return jdbcTemplate.query("SELECT id, name, contents, date FROM calendar", calendarRowMapper());
    }

    @Override
    public Optional<CalendarResponseDto> findCalendarByName(String name) {
        List<CalendarResponseDto> result = jdbcTemplate.query(
                "SELECT id, name, contents, date FROM calendar WHERE name = ?",
                calendarRowMapper(),
                name
        );
        return result.stream().findFirst();
    }

    @Override
    public boolean updateCalendar(CalendarRequestDto calendarRequestDto) {
        String name = calendarRequestDto.getName();
        String contents = calendarRequestDto.getContents();
        String password = calendarRequestDto.getPassword();
        String revisedDate =calendarRequestDto.getRevisedDate();
        System.out.println("revisedDate = " + revisedDate);

        Map<String, Object> parameters = new HashMap<>();

        Optional<Calendar> existingCalendar = jdbcTemplate.query(
                "SELECT password FROM calendar ",
                calendarPasswordRowMapper()
        ).stream().findFirst();

        if (existingCalendar.isPresent() && password.equals(existingCalendar.get().getPassword())) {

            return jdbcTemplate.update(
                    "UPDATE calendar SET name = ?, contents = ?,revised_date = ?",
                    name, contents,revisedDate
            ) > 0;
        }
        return false;
    }

    @Override
    public boolean deleteCalendar(int id,CalendarRequestDto calendarRequestDto) {
        return jdbcTemplate.update("DELETE FROM calendar WHERE name = ?", calendarRequestDto.getName()) > 0;
    }

    private RowMapper<CalendarResponseDto> calendarRowMapper() {
        return (rs, rowNum) -> new CalendarResponseDto(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("contents"),
                rs.getString("date")
        );
    }

    private RowMapper<Calendar> calendarPasswordRowMapper() {
        return (rs, rowNum) -> {
            Calendar calendar = new Calendar();
            calendar.setPassword(rs.getString("password"));
            return calendar;
        };
    }
}
