package com.example.marketplace.service.impl;

import com.example.marketplace.dto.ProductRequest;
import com.example.marketplace.dto.ProductResponse;
import com.example.marketplace.entities.Product;
import com.example.marketplace.exception.BadRequestException;
import com.example.marketplace.mapper.ProductMapper;
import com.example.marketplace.repositories.ProductRepository;
import com.example.marketplace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public void add(ProductRequest request) {
        Product product = new Product();
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setPrize(request.getPrize());
        product.setImageUrl(request.getImageUrl());
        productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> all() {
        return productMapper.toDtoS(productRepository.findAll());
    }

    @Override
    public ProductResponse byId(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty())
            throw new BadRequestException("not found product with id: "+ productId);
        return productMapper.toDto(product.get());
    }

    @Override
    public void update(Long productId, ProductRequest request) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty())
            throw new BadRequestException("not found product with id: "+ productId);
        product.get().setName(request.getName());
        product.get().setPrize(request.getPrize());
        product.get().setDescription(request.getDescription());
        product.get().setImageUrl(request.getImageUrl());
        productRepository.save(product.get());

    }
}
