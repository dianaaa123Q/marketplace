package com.example.marketplace.mapper.impl;

import com.example.marketplace.dto.BasketResponse;
import com.example.marketplace.entities.Basket;
import com.example.marketplace.mapper.BasketMapper;
import com.example.marketplace.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketMapperImpl implements BasketMapper {
    private final ProductMapper productMapper;
    @Override
    public List<BasketResponse> toDtoS(List<Basket> baskets) {
        List<BasketResponse> basketResponses = new ArrayList<>();
        for (Basket basket: baskets)
            basketResponses.add(toDto(basket));
        return basketResponses;
    }

    private BasketResponse toDto(Basket basket) {
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setId(basket.getId());
        basketResponse.setUserEmail(basket.getUser().getEmail());
        basketResponse.setTimeAdded(basket.getTimeAdded());
        basketResponse.setProductResponse(productMapper.toDto(basket.getProduct()));
        return basketResponse;
    }
}
