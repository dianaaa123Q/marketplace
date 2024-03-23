package com.example.marketplace.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;

    private List<ProductResponse> userProducts;
    private List<ProductResponse> userFavorites;
    private List<BasketResponse> basketResponses;

}
