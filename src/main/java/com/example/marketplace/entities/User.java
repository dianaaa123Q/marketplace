package com.example.marketplace.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToMany()
    private List<Product> favoriteProducts;

}
