package com.example.calendarproject.service;

import com.example.calendarproject.dto.UserRequestDto;
import com.example.calendarproject.dto.UserResponseDto;
import com.example.calendarproject.entity.UserEntity;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    String deleteUser(long id);

    UserResponseDto updateUser(long id, UserRequestDto userRequestDto);
}
