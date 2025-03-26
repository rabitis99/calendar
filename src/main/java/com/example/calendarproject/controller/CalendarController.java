package com.example.calendarproject.controller;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.entity.CalendarEntity;
import com.example.calendarproject.service.CalendarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Calendar API Controller
 * 일정(Calender) 관련 CRUD 기능을 제공하는 컨트롤러
 */
@RestController
@RequestMapping("/calendars")
@Validated

public class CalendarController  {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping
    public ResponseEntity<CalendarResponseDto> createCalendar(@Valid @RequestBody  CalendarRequestDto calendarRequestDto){

        return new ResponseEntity<>(calendarService.createCalendar(calendarRequestDto),HttpStatus.CREATED) ;
    }

    @GetMapping
    public ResponseEntity<List<CalendarResponseDto>> findAllCalendars(){

        return new ResponseEntity<>(calendarService.findAllCalendars(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CalendarResponseDto>> findCalendarsByUserId(
            @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long userId){

        return new ResponseEntity<>(calendarService.findCalendarsByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<List<CalendarResponseDto>> findAllCalendarsById(
             @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long id,
             @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long userId){

        return new ResponseEntity<>(calendarService.findAllCalendarsById(id),HttpStatus.OK);
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity<CalendarResponseDto> updateCalendarByEmail(
            @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long id,
            @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long userId
            , @Valid @RequestBody  CalendarRequestDto calendarRequestDto
            ,@RequestParam @Email (message = "이메일 형식으로 입력하셔야 합니다.") String email){
        return new ResponseEntity<>(calendarService.updateCalendar(id, calendarRequestDto,email),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteCalendar(
            @RequestParam @Email(message = "이메일 형식으로 입력하셔야 합니다.")  String email,
            @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long id,
            @PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long userId)
            {
            return new ResponseEntity<>(calendarService.deleteCalendar(id,email,userId),HttpStatus.OK);
    }
}
