package com.example.task1.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.task1.model.Product;

@Service
public class ShoppingCartService {

    public Map<Product, Integer> products = new HashMap<>();

    public Map<Product, Integer> getAllCartItems() {
        return products;
    }

    // Add a product to the cart (or update quantity if it exists)
    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    // Calculate the total sum of all products in the cart
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += Integer.parseInt(product.getPrice()) * quantity;
        }
        return total;
    }

}
