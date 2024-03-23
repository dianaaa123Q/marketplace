package com.example.marketplace.controllers;

import com.example.marketplace.dto.ProductRequest;
import com.example.marketplace.dto.ProductResponse;
import com.example.marketplace.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public void add(@RequestBody ProductRequest request){
        productService.add(request);
    }
    @DeleteMapping("/{productId}")
    public void delete(@PathVariable Long productId){
        productService.delete(productId);
    }
    @GetMapping("/all")
    public List<ProductResponse> all(){
        return productService.all();
    }
    @GetMapping("/{productId}")
    public ProductResponse byId(@PathVariable Long productId){
        return productService.byId(productId);
    }
    @PutMapping("/update/{productId}")
    public void update(@PathVariable Long productId, @RequestBody ProductRequest request){
        productService.update(productId, request);
    }
}
