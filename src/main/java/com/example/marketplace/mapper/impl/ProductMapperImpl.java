package com.example.marketplace.mapper.impl;

import com.example.marketplace.dto.ProductResponse;
import com.example.marketplace.entities.Product;
import com.example.marketplace.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public List<ProductResponse> toDtoS(List<Product> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product: products)
            productResponses.add(toDto(product));
        return productResponses;
    }

    @Override
    public ProductResponse toDto(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setDescription(product.getDescription());
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrize(product.getPrize());
        productResponse.setImageUrl(product.getImageUrl());
        return productResponse;
    }
}
