package com.example.calendarproject.repository;

import com.example.calendarproject.entity.PageEntity;

public interface PageRepository {
    PageEntity getPage(PageEntity pageEntity,int totalPage);
    int getTotalpage();
}
