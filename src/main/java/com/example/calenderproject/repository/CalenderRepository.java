package com.example.calenderproject.repository;

import com.example.calenderproject.dto.CalenderRequestDto;
import com.example.calenderproject.dto.CalenderResponseDto;
import com.example.calenderproject.entity.Calender;

import java.util.List;

public interface CalenderRepository {

    CalenderResponseDto creatCalender(Calender calender);

    List<CalenderResponseDto> findAllCalender();

    CalenderResponseDto findCalenderByName(CalenderRequestDto calenderRequestDto);

    int reviseCalender(CalenderRequestDto calenderRequestDto);

    int deleteCalender(CalenderRequestDto calenderRequestDto);
}
