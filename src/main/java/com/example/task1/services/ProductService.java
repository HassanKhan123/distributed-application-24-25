package com.example.task1.services;

import org.springframework.stereotype.Service;

import com.example.task1.model.Product;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;


// @Component
@Service
public class ProductService {

    private Map<String, Product> db = new HashMap<>() {
        {
            put("1", new Product("1", "Shirt", "200", "xl", "black"));
            put("2", new Product("2", "Full Sleeves Shirt", "300", "l", "red"));
            put("3", new Product("3", "Jeans", "350", "m", "blue"));
            put("4", new Product("4", "Coat", "500", "sm", "green"));
            put("5", new Product("5", "Half sleeves shirt", "100", "xxl", "pink"));

        }
    };

    public Collection<Product> get() {
        return db.values();
    }

    public Product get(String id) {

        return db.get(id);
    }

    public Product remove(String id) {

        return db.remove(id);
    }

    public List<Product> filterProductsByColor(String color) {
        System.out.println("Filtering by color: " + color); // Debug
        List<Product> filteredProducts = db.values().stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        System.out.println("Filtered products: " + filteredProducts); // Debug
        return filteredProducts;
    }

    public List<Product> filterProductsBySize(String size) {
        System.out.println("Filtering by size: " + size); // Debug
        List<Product> filteredProducts = db.values().stream()
                .filter(product -> product.getSize().equalsIgnoreCase(size))
                .collect(Collectors.toList());
        System.out.println("Filtered products: " + filteredProducts); // Debug
        return filteredProducts;
    }

    public String addProduct(Product product) {
        if (product.getId() == null || db.containsKey(product.getId())) {
            return "Invalid product ID or already exists.";
        }

        db.put(product.getId(), product);
        return "Product added successfully.";
    }

    public String deleteProduct(String id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return "Product deleted successfully.";
        } else {
            return "Product not found.";
        }
    }

    public String updateProduct(Product product) {
        if (product.getId() == null || !db.containsKey(product.getId())) {
            return "Invalid product ID or product not found.";
        }

        db.put(product.getId(), product);
        return "Product updated successfully.";
    }

}