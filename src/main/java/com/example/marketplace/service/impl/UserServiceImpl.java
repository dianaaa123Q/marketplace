package com.example.marketplace.service.impl;

import com.example.marketplace.dto.UserRequest;
import com.example.marketplace.entities.User;
import com.example.marketplace.exception.BadRequestException;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void add(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadRequestException("user with this email is already exist! "+ request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setLastname(request.getLastname());
        user.setFirstname(request.getFirstname());
        userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
