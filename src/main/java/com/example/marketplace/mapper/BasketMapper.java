package com.example.marketplace.mapper;

import com.example.marketplace.dto.BasketResponse;
import com.example.marketplace.entities.Basket;

import java.util.List;

public interface BasketMapper {
    List<BasketResponse> toDtoS(List<Basket> baskets);
}
