package com.example.marketplace.mapper;

import com.example.marketplace.dto.UserResponse;
import com.example.marketplace.entities.User;

import java.util.List;

public interface UserMapper {
    List<UserResponse> toDtoS(List<User> all);

    UserResponse toDto(User user);
}
