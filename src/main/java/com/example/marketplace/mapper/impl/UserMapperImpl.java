package com.example.marketplace.mapper.impl;

import com.example.marketplace.dto.UserResponse;
import com.example.marketplace.entities.User;
import com.example.marketplace.mapper.BasketMapper;
import com.example.marketplace.mapper.ProductMapper;
import com.example.marketplace.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final ProductMapper productMapper;
    private final BasketMapper basketMapper;
    @Override
    public List<UserResponse> toDtoS(List<User> all) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: all)
            userResponses.add(toDto(user));
        return userResponses;
    }

    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());

        userResponse.setUserProducts(productMapper.toDtoS(user.getProducts()));
        userResponse.setUserFavorites(productMapper.toDtoS(user.getFavoriteProducts()));
        userResponse.setBasketResponses(basketMapper.toDtoS(user.getBaskets()));
        return userResponse;
    }
}
