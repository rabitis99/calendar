package com.example.calendarproject.repository;

import com.example.calendarproject.entity.CalendarEntity;
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
    public CalendarEntity createCalendar(CalendarEntity calendar) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", calendar.getName());
        parameters.put("task", calendar.getTask());
        parameters.put("created_at", calendar.getCreatedAt());
        parameters.put("password", calendar.getPassword());
        parameters.put("user_id",calendar.getUserId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        calendar.setId(key.intValue());
        return calendar;
    }

    @Override
    public List<CalendarEntity> findAllCalendars() {
        return jdbcTemplate.query("SELECT * FROM calendar", calendarRowMapper());
    }

    @Override
    public Optional<CalendarEntity> findCalendarByName(String name) {
        List<CalendarEntity> result = jdbcTemplate.query(
                "SELECT * FROM calendar WHERE name = ?",
                calendarRowMapper(),
                name
        );
        return result.stream().findFirst();
    }

    @Override
    public boolean updateCalendar(CalendarEntity calendar, String email) {
        String name = calendar.getName();
        String contents = calendar.getTask();
        String password = calendar.getPassword();
        String revisedDate = calendar.getUpdatedAt();

        Optional<String> existingPassword = jdbcTemplate.query(
                "SELECT password FROM calendar WHERE name = ?",
                (rs, rowNum) -> rs.getString("password"),
                name
        ).stream().findFirst();

        if (existingPassword.isPresent() && password.equals(existingPassword.get())) {
            return jdbcTemplate.update(
                    "UPDATE calendar inner join user on calendar.name=user.name SET task = ?, calendar.updated_at = ? WHERE user.email = ?",
                    contents, revisedDate, email
            ) > 0;
        }
        return false;
    }

    @Override
    public String deleteCalendar(long id, long userId ) {
        boolean deleteCalendarBoolean =jdbcTemplate.update("DELETE FROM calendar WHERE id = ? AND user_id = ?", id,userId) > 0;
        if(!deleteCalendarBoolean){
            return "삭제가 됐습니다";
        }
        else {
            return "삭제에 실패했습니다";
        }
    }

    @Override
    public List<CalendarEntity> findCalendarById(long id) {
        return jdbcTemplate.query("SELECT * FROM calendar where id=?",
                calendarRowMapper(), id);
    }
    @Override
    public List<Long> checkUserId() {

        return jdbcTemplate.query("SELECT user_id FROM calendar",
                (rs, rowNum) ->  rs.getLong("user_id"));

    }
    @Override
    public List<Long> checkId() {
        return jdbcTemplate.query("SELECT id FROM calendar",
                (rs, rowNum) ->  rs.getLong("id"));
    }

    @Override
    public List<CalendarEntity> findCalendarsByUserId(long userId) {
        return jdbcTemplate.query("SELECT * FROM calendar where user_id=?",
                calendarRowMapper(),
                userId);
    }

    private RowMapper<CalendarEntity> calendarRowMapper() {
        return (rs, rowNum) -> {
            CalendarEntity calendar = new CalendarEntity();
            calendar.setId(rs.getInt("id"));
            calendar.setName(rs.getString("name"));
            calendar.setTask(rs.getString("task"));
            calendar.setCreatedAt(rs.getString("created_at"));
            calendar.setUpdatedAt(rs.getString("updated_at"));
            return calendar;
        };
    }
}
