package com.example.marketplace.service.impl;

import com.example.marketplace.entities.Basket;
import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.User;
import com.example.marketplace.exception.NotFoundException;
import com.example.marketplace.repositories.BasketRepository;
import com.example.marketplace.repositories.ProductRepository;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    @Override
    public void add(Long userId, Long productId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(productId);
        if (user.isEmpty())
            throw new NotFoundException("not found user with id: "+ userId, HttpStatus.NOT_FOUND);
        if (product.isEmpty())
            throw new NotFoundException("not found product with id: "+ productId, HttpStatus.NOT_FOUND);
        Basket basket = new Basket();
        basket.setUser(user.get());
        basket.setProduct(product.get());
        basket.setTimeAdded(LocalDateTime.now());
        basketRepository.save(basket);
    }
}
