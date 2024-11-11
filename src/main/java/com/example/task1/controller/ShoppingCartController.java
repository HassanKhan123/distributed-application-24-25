package com.example.task1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.task1.model.AddToCartRequest;
import com.example.task1.model.CartItemResponse;
import com.example.task1.model.Product;
import com.example.task1.services.ProductService;
import com.example.task1.services.ShoppingCartService;

@RestController
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartItemResponse>> getAllCartItems() {
        List<CartItemResponse> responseList = shoppingCartService.getAllCartItems().entrySet().stream()
                .map(entry -> new CartItemResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addProductToCart(@RequestBody AddToCartRequest request) {

        Product product = productService.get(request.getProductId());

        if (product != null) {
            shoppingCartService.addProduct(product, request.getQuantity());
        }
        return ResponseEntity.ok("Successfully added to cart");
    }

}
