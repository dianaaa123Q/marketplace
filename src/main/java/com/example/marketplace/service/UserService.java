package com.example.marketplace.service;

import com.example.marketplace.dto.UserRequest;
import com.example.marketplace.dto.UserResponse;

import java.util.List;

public interface UserService {
    void add(UserRequest request);

    void delete(Long userId);

    List<UserResponse> getAll();

    UserResponse byId(Long userId);
}
