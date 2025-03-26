package com.example.calendarproject.service;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.entity.CalendarEntity;
import com.example.calendarproject.exception.CustomException;
import com.example.calendarproject.repository.CalendarRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.calendarproject.exception.ErrorCode.ID_NOT_FOUND;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto createCalendar(CalendarRequestDto calendarRequestDto) {
        CalendarEntity calendar = new CalendarEntity(calendarRequestDto);
        // 날짜를 입력받지 않고 여기서 생성 why? Repository에서 생성하면 너무 할일이 많아진다고 생각

        LocalDateTime today = LocalDateTime.now(ZoneId.of("Asia/Seoul"));;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = today.format(formatter);

        calendar.setCreatedAt(formattedDate);

        return new CalendarResponseDto(calendarRepository.createCalendar(calendar));
    }

    @Override
    public List<CalendarResponseDto> findAllCalendars() {

        List<CalendarResponseDto> calendarResponseDtoList=new ArrayList<>();

        for(CalendarEntity calendar:calendarRepository.findAllCalendars()){
            calendarResponseDtoList.add(new CalendarResponseDto(calendar));
        }

        return calendarResponseDtoList;
    }
    @Override
    public CalendarResponseDto updateCalendar(long id, CalendarRequestDto calendarRequestDto, String email) {
        CalendarEntity calendar=new CalendarEntity(calendarRequestDto);

        LocalDateTime today = LocalDateTime.now(ZoneId.of("Asia/Seoul"));;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = today.format(formatter);

        calendar.setUpdatedAt(formattedDate);

        boolean updated = calendarRepository.updateCalendar(calendar,email);

        return updated ? new CalendarResponseDto(Objects.requireNonNull(calendarRepository.findCalendarByName(calendarRequestDto.getName()).orElse(null))) : null;
    }


    @Override
    public String deleteCalendar(long id,String email,long userId) {

        if (!(calendarRepository.checkId().contains(id))){
            throw new CustomException(ID_NOT_FOUND);
        }
        if (!(calendarRepository.checkUserId().contains(userId))){
            throw new CustomException(ID_NOT_FOUND);
        }

        return calendarRepository.deleteCalendar(userId,id);
    }

    @Override
    public List<CalendarResponseDto> findAllCalendarsById(long id) {
        if (!(calendarRepository.checkId().contains(id))){
            
            throw new CustomException(ID_NOT_FOUND);
        }
        List<CalendarResponseDto> calendarResponseDtoList=new ArrayList<>();
        for(CalendarEntity calendarEntity:calendarRepository.findCalendarById(id)){
            calendarResponseDtoList.add(new CalendarResponseDto(calendarEntity));
        }
        return calendarResponseDtoList;
    }

    @Override
    public List<CalendarResponseDto> findCalendarsByUserId(long userId) {
        if (!(calendarRepository.checkUserId().contains(userId))){
            throw new CustomException(ID_NOT_FOUND);
        }
        List<CalendarResponseDto> calendarResponseDtoList=new ArrayList<>();
        for(CalendarEntity calendar:calendarRepository.findAllCalendars()){
            calendarResponseDtoList.add(new CalendarResponseDto(calendar));
        }
        return calendarResponseDtoList;
    }
}