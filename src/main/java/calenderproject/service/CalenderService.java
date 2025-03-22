package calenderproject.service;

import calenderproject.dto.CalenderRequestDto;
import calenderproject.dto.CalenderResponseDto;

import java.util.List;

public interface CalenderService {



    CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto);

    List<CalenderResponseDto> findAllCalender();

    CalenderResponseDto reviseCalender(CalenderRequestDto calenderRequestDto);

    void deleteCalender(CalenderRequestDto calenderRequestDto);

}
