
package com.example.task1.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.task1.model.Product;
import com.example.task1.model.ProductDetailDTO;
import com.example.task1.services.ProductDetailFacade;
import com.example.task1.services.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductDetailFacade productDetailFacade;

    @Autowired
    public ProductController(ProductService productService, ProductDetailFacade productDetailFacade) {
        this.productService = productService;
        this.productDetailFacade = productDetailFacade;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/products")
    public Collection<Product> get() {
        return productService.get();

    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    // Route for filtering by color
    @GetMapping("/products/filterByColor")
    public List<Product> getProductsByColor(@RequestParam String color) {
        return productService.filterProductsByColor(color);
    }

    // // Route for filtering by size
    // @GetMapping("/products/filterBySize")
    // public List<Product> getProductsBySize(@RequestParam String size) {
    // return productService.filterProductsBySize(size);
    // }

    @GetMapping("/products/{id}")
    public ProductDetailDTO get(@PathVariable("id") Long id) {
        ProductDetailDTO productDetailDTO = productDetailFacade.getProductDetail(id);

        if (productDetailDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return productDetailDTO;
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);

        return ResponseEntity.ok(newProduct);

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String result = productService.deleteProduct(id);
        if (result.equals("Product deleted successfully.")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/products")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        String result = productService.updateProduct(product);
        if (result.equals("Product updated successfully.")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

}
