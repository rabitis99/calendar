package com.example.calendarproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

/**
 * 페이지에서 간략하게 이름 할일,이름 날짜 간락하게 보이는 정도
 */
public class CalendarInPage {

    String task;
    String name;
    String date;

}
