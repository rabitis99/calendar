package com.example.calendarproject.service;

import com.example.calendarproject.dto.UserRequestDto;
import com.example.calendarproject.dto.UserResponseDto;
import com.example.calendarproject.entity.UserEntity;
import com.example.calendarproject.exception.CustomException;
import com.example.calendarproject.exception.ErrorCode;
import com.example.calendarproject.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        UserEntity user=new UserEntity(userRequestDto);


        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        user.setCreatedAt(formattedDate);

        return new UserResponseDto(userRepository.createUser(user));
    }


    @Override
    public String deleteUser(long id) {

        if (!userRepository.cheakUserId().contains(id)){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return userRepository.deleteUser(id);
    }

    @Override
    public UserResponseDto updateUser(long id, UserRequestDto userRequestDto) {
        if (!userRepository.cheakUserId().contains(id)){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        UserEntity user=new UserEntity(userRequestDto);

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        user.setUpdatedAt(formattedDate);

        return new UserResponseDto(userRepository.updateUser(user,id));
    }
}
