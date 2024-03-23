package com.example.marketplace.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private Integer prize;
    private String imageUrl;
}
