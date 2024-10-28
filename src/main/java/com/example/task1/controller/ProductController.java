
package com.example.task1.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.task1.model.Product;
import com.example.task1.services.ProductService;

@RestController
public class ProductController {

   private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/products")
    public Collection<Product> get() {
        return productService.get();

    }

     // Route for filtering by color
     @GetMapping("/products/filterByColor")
     public List<Product> getProductsByColor(@RequestParam String color) {
         return productService.filterProductsByColor(color);
     }
 
     // Route for filtering by size
     @GetMapping("/products/filterBySize")
     public List<Product> getProductsBySize(@RequestParam String size) {
         return productService.filterProductsBySize(size);
     }
 

    

    @GetMapping("/products/{id}")
    public Product get(@PathVariable("id") String id) {
        Product product = productService.get(id);

        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return product;
    }

   
}
