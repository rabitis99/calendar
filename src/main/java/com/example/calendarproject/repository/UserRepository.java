package com.example.calendarproject.repository;

import com.example.calendarproject.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    UserEntity createUser(UserEntity user);

    UserEntity updateUser(UserEntity user, long id);

    String deleteUser(long id);

    List<Long> cheakUserId();
}
