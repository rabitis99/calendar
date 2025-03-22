package calenderproject.controller;

import calenderproject.dto.CalenderRequestDto;
import calenderproject.dto.CalenderResponseDto;
import calenderproject.service.CalenderService;
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
    @PatchMapping
    public CalenderResponseDto reviseCalender(@RequestBody CalenderRequestDto calenderRequestDto){
        return calenderService.reviseCalender(calenderRequestDto);
    }
    @DeleteMapping
    public void deleteCalender(@RequestBody CalenderRequestDto calenderRequestDto){

        calenderService.deleteCalender(calenderRequestDto);
    }


}
