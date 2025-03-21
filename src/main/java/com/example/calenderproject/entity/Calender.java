package com.example.calenderproject.entity;

import com.example.calenderproject.dto.CalenderRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Calender {
    @Setter
    int id;

    String name;
    String date;
    String revisedDate;
    String password;
    String contents;

    public Calender(CalenderRequestDto requestDto){
        this.name=requestDto.getName();
        this.date=requestDto.getDate();
        this.password=requestDto.getPassword();
        this.contents=requestDto.getContents();
    }


}
