package com.example.calendarproject.dto;


import com.example.calendarproject.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class UserResponseDto {
    String name;
    String email;
    String updatedAt;
    String createdAt;
    long id;

    public  UserResponseDto(UserEntity user){
        this.name= user.getName();
        this.email= user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.id= user.getId();
    }
}
