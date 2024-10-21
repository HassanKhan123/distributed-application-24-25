package com.example.task1;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductController {

    private List<Product> products = List.of(
        new Product("1", "test 1","23","red","xl"),
        new Product("2", "test 2","23","red","xl"),
        new Product("3", "test 3","23","red","xl"),
        new Product("4", "test 4","23","red","xl"),
        new Product("5", "test 5","23","red","xl"));
    

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/products")
    public Collection<Product> get() {
        return products;

    }



    @GetMapping("/products/{id}")
    public Product get(@PathVariable String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}