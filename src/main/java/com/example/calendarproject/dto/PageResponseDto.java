package com.example.calendarproject.dto;

import com.example.calendarproject.entity.CalendarInPage;
import com.example.calendarproject.entity.PageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto {
    int currentPage;
    int totalPage;
    List<CalendarInPage> content;

    public PageResponseDto(PageEntity page){
        this.currentPage=page.getCurrentPage();
        this.totalPage=page.getTotalPage();
        this.content=page.getContent();
    }
}
