package com.example.calendarproject.service;

import com.example.calendarproject.dto.PageResponseDto;
import com.example.calendarproject.entity.PageEntity;
import com.example.calendarproject.repository.PageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageServiceImpl implements PageService{

    private final PageRepository pageRepository;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public PageResponseDto getPages(int pageNum, int pageSize) {
        PageEntity pageEntity = new PageEntity(pageNum,pageSize);
        int totalRecords=pageRepository.getTotalpage();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        pageEntity.setTotalPage(totalPages);

        if((pageNum-1)*pageSize>totalPages){
            pageEntity.setContent(List.of());
        }
        else {
            pageEntity=pageRepository.getPage(pageEntity,totalPages);
        }

        return new PageResponseDto(pageEntity) ;
    }
}
