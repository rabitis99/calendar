package com.example.calendarproject.controller;

import com.example.calendarproject.dto.PageResponseDto;
import com.example.calendarproject.service.PageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pages")
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }
    @GetMapping
    public ResponseEntity<PageResponseDto> gerPage(@RequestParam int pageNum, int pageSize){

        return new ResponseEntity<>(pageService.getPages(pageNum,pageSize), HttpStatus.OK);
    }

}
