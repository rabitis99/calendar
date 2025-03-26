package com.example.calendarproject.service;

import com.example.calendarproject.dto.PageResponseDto;


public interface PageService {
    PageResponseDto getPages(int pageNum,int pageSize);

}
