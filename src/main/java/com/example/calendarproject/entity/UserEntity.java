package com.example.calendarproject.entity;

import com.example.calendarproject.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {
    String name;
    String email;
    String revisedDate;
    String date;
    int id;

    public User(UserRequestDto userRequestDto){
        this.name=userRequestDto.getName();
        this.email= userRequestDto.getEmail();
    }

}
