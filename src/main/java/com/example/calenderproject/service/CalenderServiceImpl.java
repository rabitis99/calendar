package com.example.calenderproject.service;

import com.example.calenderproject.dto.CalenderRequestDto;
import com.example.calenderproject.dto.CalenderResponseDto;
import com.example.calenderproject.entity.Calender;
import com.example.calenderproject.repository.CalenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService{

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    @Override
    public CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto) {

        Calender calender=new Calender(calenderRequestDto);

        return calenderRepository.creatCalender(calender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalender() {

        return calenderRepository.findAllCalender();
    }


}
