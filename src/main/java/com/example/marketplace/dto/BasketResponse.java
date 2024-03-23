package com.example.marketplace.dto;

import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BasketResponse {
    private Long id;


    private String userEmail;

    private ProductResponse productResponse;

    private LocalDateTime timeAdded;
}
