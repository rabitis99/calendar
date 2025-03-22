package calenderproject.repository;

import calenderproject.dto.CalenderRequestDto;
import calenderproject.dto.CalenderResponseDto;
import calenderproject.entity.Calender;

import java.util.List;

public interface CalenderRepository {

    CalenderResponseDto creatCalender(Calender calender);

    List<CalenderResponseDto> findAllCalender();

    CalenderResponseDto findCalenderByName(CalenderRequestDto calenderRequestDto);

    int reviseCalender(CalenderRequestDto calenderRequestDto);

    int deleteCalender(CalenderRequestDto calenderRequestDto);
}
