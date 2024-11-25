package com.example.task1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Product;
import com.example.task1.repository.InventoryRepository;
import com.example.task1.repository.ProductRepository;

import java.util.List;
import java.util.Collection;

// @Component
@Service
public class ProductService {

    private final InventoryService inventoryService;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public ProductService(InventoryService inventoryService, ProductRepository productRepository,
            InventoryRepository inventoryRepository) {
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Collection<Product> get() {
        return productRepository.findAll();
    }

    public Product get(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    public void remove(Long id) {

        productRepository.deleteById(id);
    }

    public List<Product> filterProductsByColor(String color) {
        System.out.println("Filtering by color: " + color); // Debug
        List<Product> filteredProducts = productRepository.findByProductColorIgnoreCase(color);
        System.out.println("Filtered products: " + filteredProducts); // Debug
        return filteredProducts;
    }

    // public List<Product> filterProductsBySize(String size) {
    // System.out.println("Filtering by size: " + size); // Debug
    // List<Product> filteredProducts =
    // productRepository.findBySizeIgnoreCase(size);
    // System.out.println("Filtered products: " + filteredProducts); // Debug
    // return filteredProducts;
    // }

    public List<Product> searchProductsByName(String name) {
        return productRepository.searchByProductNameStartingWith(name); // or use searchByNameStartingWith(name) if
                                                                        // using
        // @Query
    }

    public Product addProduct(Product product) {

        Product newProduct = productRepository.save(product);
        inventoryService.addStockForProductId(newProduct.getId(), 2);

        return product;
    }

    public String deleteProduct(Long id) {
        inventoryRepository.findByProductId(
                id)
                .ifPresent(inventory -> inventoryRepository.delete(inventory));

        // Then delete the product
        productRepository.deleteById(id);
        productRepository.deleteById(id);

        return "Product deleted successfully.";

    }

    public String updateProduct(Product product) {

        productRepository.save(product);
        return "Product updated successfully.";
    }

}