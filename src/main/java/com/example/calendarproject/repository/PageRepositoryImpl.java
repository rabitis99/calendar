package com.example.calendarproject.repository;

import com.example.calendarproject.entity.CalendarInPage;
import com.example.calendarproject.entity.PageEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PageRepositoryImpl implements PageRepository {
    private final JdbcTemplate jdbcTemplate;

    public PageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PageEntity getPage(PageEntity pageEntity, int totalPage) {
        int pageNum = pageEntity.getPageNum();
        int pageSize = pageEntity.getPageSize();
        int totalRecords = getTotalRecords();

        // 총 페이지 수 계산
        int calculatedTotalPages = (int) Math.ceil((double) totalRecords / pageSize);

        // 데이터 조회 SQL
        String sql = "SELECT name, contents, date FROM calendar ORDER BY id DESC LIMIT ? OFFSET ?";
        List<CalendarInPage> calendarInPageList = jdbcTemplate.query(
                sql,
                PageCalendarRowMapper(),
                pageSize,
                (pageNum - 1) * pageSize
        );

        // 요청한 페이지가 범위를 벗어났다면 빈 배열 반환(마지막 null 값을 줌)
        while (pageNum*pageSize>totalRecords|| pageNum < 1){

            calendarInPageList.add(new CalendarInPage());
            totalRecords++;
        }

        pageEntity.setContent(calendarInPageList);
        return pageEntity;
    }

    private RowMapper<CalendarInPage> PageCalendarRowMapper() {
        return (rs, rowNum) -> new CalendarInPage(
                rs.getString("name"),
                rs.getString("contents"),
                rs.getString("creatd_at")
        );
    }

    @Override
    public int getTotalpage() {
        return getTotalRecords();
    }

    private int getTotalRecords() {
        String sql = "SELECT COUNT(*) FROM calendar";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

