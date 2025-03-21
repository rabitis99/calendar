package com.example.calenderproject.service;

import com.example.calenderproject.dto.CalenderRequestDto;
import com.example.calenderproject.dto.CalenderResponseDto;

import java.util.List;

public interface CalenderService {



    CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto);

    List<CalenderResponseDto> findAllCalender();


}
