package calenderproject.service;

import calenderproject.dto.CalenderRequestDto;
import calenderproject.dto.CalenderResponseDto;
import calenderproject.entity.Calender;
import calenderproject.repository.CalenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalenderServiceImpl implements CalenderService {

    private final CalenderRepository calenderRepository;

    public CalenderServiceImpl(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    @Override
    public CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto) {

        Calender calender=new Calender();
        calender.CalenderbycalenderRequestDto(calenderRequestDto);

        return calenderRepository.creatCalender(calender);
    }

    @Override
    public List<CalenderResponseDto> findAllCalender() {

        return calenderRepository.findAllCalender();
    }

    @Override
    public CalenderResponseDto reviseCalender(CalenderRequestDto calenderRequestDto) {

        calenderRepository.reviseCalender(calenderRequestDto);
        return calenderRepository.findCalenderByName(calenderRequestDto);
    }

    @Override
    public void deleteCalender(CalenderRequestDto calenderRequestDto) {

        calenderRepository.deleteCalender(calenderRequestDto);
    }



}
