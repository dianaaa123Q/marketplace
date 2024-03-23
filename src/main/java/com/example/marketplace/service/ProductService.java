package com.example.marketplace.service;

import com.example.marketplace.dto.ProductRequest;
import com.example.marketplace.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void add(ProductRequest request);

    void delete(Long productId);

    List<ProductResponse> all();

    ProductResponse byId(Long productId);

    void update(Long productId, ProductRequest request);
}
