package com.example.calendarproject.entity;

import com.example.calendarproject.dto.UserRequestDto;
import jakarta.validation.constraints.Email;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserEntity {

    String name;
    String email;
    String updatedAt;
    String createdAt;
    long id;

    public UserEntity(UserRequestDto userRequestDto){
        this.name=userRequestDto.getName();
        this.email= userRequestDto.getEmail();
    }

}
