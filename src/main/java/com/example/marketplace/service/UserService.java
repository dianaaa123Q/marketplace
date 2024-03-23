package com.example.marketplace.service;

import com.example.marketplace.dto.UserRequest;

public interface UserService {
    void add(UserRequest request);

    void delete(Long userId);
}
