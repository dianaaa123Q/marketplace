package com.example.marketplace.mapper;

import com.example.marketplace.dto.ProductResponse;
import com.example.marketplace.entities.Product;

import java.util.List;

public interface ProductMapper {
    List<ProductResponse> toDtoS(List<Product> products);

    ProductResponse toDto(Product product);
}
