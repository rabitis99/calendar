package com.example.calenderproject.repository;

import com.example.calenderproject.dto.CalenderResponseDto;
import com.example.calenderproject.entity.Calender;

import java.util.List;

public interface CalenderRepository {

    CalenderResponseDto creatCalender(Calender calender);

    List<CalenderResponseDto> findAllCalender();


}
