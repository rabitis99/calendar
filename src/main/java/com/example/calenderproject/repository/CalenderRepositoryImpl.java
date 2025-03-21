package com.example.calenderproject.repository;

import com.example.calenderproject.dto.CalenderResponseDto;
import com.example.calenderproject.entity.Calender;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Repository
public class CalenderRepositoryImpl implements CalenderRepository {

    private final JdbcTemplate jdbcTemplate;

    public CalenderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CalenderResponseDto creatCalender(Calender calender) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calender").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", calender.getName());
        parameters.put("contents", calender.getContents());
        parameters.put("date", calender.getDate());
        parameters.put("password", calender.getPassword());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        calender.setId(key.intValue());
        CalenderResponseDto calenderResponseDto = new CalenderResponseDto();
        calenderResponseDto.CalenderResponseDtoCalender(calender);//id가 변화된 객체
        return calenderResponseDto;
    }

    @Override
    public List<CalenderResponseDto> findAllCalender() {

        return jdbcTemplate.query("select name, contents, date, id from calender", calenderRowMapper());
    }

    private RowMapper<CalenderResponseDto> calenderRowMapper() {
        return new RowMapper<CalenderResponseDto>() {
            @Override
            public CalenderResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                CalenderResponseDto calenderResponseDto = new CalenderResponseDto();

                calenderResponseDto.CalenderResponseDtoRepository(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getString("date")
                );
                return calenderResponseDto;
            }
        };
    }
}
