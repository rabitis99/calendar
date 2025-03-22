package calenderproject.entity;

import calenderproject.dto.CalenderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Calender {

    int id;
    String revisedDate;
    String name;
    String date;
    String password;
    String contents;

    public Calender() {

    }

    public void CalenderbycalenderRequestDto(CalenderRequestDto requestDto){
        this.name=requestDto.getName();
        this.date=requestDto.getDate();
        this.password=requestDto.getPassword();
        this.contents=requestDto.getContents();
    }
}
