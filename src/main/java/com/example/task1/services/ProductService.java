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
            put("1", new Product("1", "test 1", "23", "xl", "black"));
            put("2", new Product("2", "test 2", "23", "l", "red"));
            put("3", new Product("3", "test 3", "23", "m", "blue"));
            put("4", new Product("4", "test 4", "23", "sm", "green"));
            put("5", new Product("5", "test 5", "23", "xxl", "pink"));

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

    // public Product save(String fileName, String contentType, byte[] data) {
    //     Product photo = new Product();

    //     photo.setId(UUID.randomUUID().toString());
    //     photo.setFileName(fileName);
    //     photo.setData(data);
    //     photo.setContentType(contentType);
    //     db.put(photo.getId(), photo);
    //     return photo;

    // }

}