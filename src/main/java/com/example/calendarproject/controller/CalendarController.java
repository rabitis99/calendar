package com.example.calendarproject.controller;

import com.example.calendarproject.dto.CalendarRequestDto;
import com.example.calendarproject.dto.CalendarResponseDto;
import com.example.calendarproject.service.CalendarService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Calendar API Controller
 * 일정(Calender) 관련 CRUD 기능을 제공하는 컨트롤러
 */
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * 새로운 일정을 생성합니다.
     * @param calendarRequestDto 일정 생성 요청 DTO
     * @return 생성된 일정 정보
     */
    @PostMapping
    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto calendarRequestDto){
        return calendarService.createCalendar(calendarRequestDto);
    }

    /**
     * 모든 일정을 조회합니다.
     * @return 일정 목록
     */
    @GetMapping
    public List<CalendarResponseDto> findAllCalendars(){
        return calendarService.findAllCalendars();
    }

    /**
     * 일정을 수정합니다.
     * @param id 일정 ID
     * @param calendarRequestDto 수정할 일정 정보
     * @return 수정된 일정 정보
     */
    @PutMapping("/{id}")
    public CalendarResponseDto reviseCalendar(@PathVariable int id, @RequestBody CalendarRequestDto calendarRequestDto){
        return calendarService.updateCalendar(id, calendarRequestDto);
    }

    /**
     * 일정을 삭제합니다.
     * @param id 삭제할 일정 ID
     */
    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable int id,@RequestBody CalendarRequestDto calendarRequestDto){
        calendarService.deleteCalendar(id,calendarRequestDto);
    }
}
