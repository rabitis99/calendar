package com.example.calendarproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PageEntity {
    int currentPage;
    int totalPage;
    int pageNum;
    int pageSize;
    List<CalendarInPage> content;

    public PageEntity(int pageNum,int pageSize){
        this.pageNum=pageNum;
        this.pageSize=pageSize;
    }


}
