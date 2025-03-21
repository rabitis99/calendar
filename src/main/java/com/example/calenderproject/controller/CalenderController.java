package com.example.calenderproject.controller;

import com.example.calenderproject.dto.CalenderRequestDto;
import com.example.calenderproject.dto.CalenderResponseDto;
import com.example.calenderproject.entity.Calender;
import com.example.calenderproject.service.CalenderService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calender")

public class CalenderController {

    private final CalenderService calenderService;

    public CalenderController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @PostMapping
    public CalenderResponseDto createCalender(@RequestBody CalenderRequestDto calenderRequestDto){

        return calenderService.createCalender(calenderRequestDto);
    }
    @GetMapping
    public List<CalenderResponseDto> findAllCalender(){

        return calenderService.findAllCalender();
    }


}
