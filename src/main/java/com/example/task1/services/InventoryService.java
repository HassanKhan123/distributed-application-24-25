package com.example.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Inventory;
import com.example.task1.model.Product;
import com.example.task1.repository.InventoryRepository;
import com.example.task1.repository.ProductRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    // Method to get all stock values
    public List<Inventory> getStocks() {
        return inventoryRepository.findAll();
    }

    // Method to get the stock count for a product ID
    public int getStockForProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .map(Inventory::getStock)
                .orElse(0); // Default to 0 if product is not found
    }

    // Method to reduce the stock for a product ID
    public boolean reduceStockForProductId(Long productId, int quantity) {
        return inventoryRepository.findByProductId(productId).map(inventory -> {
            if (inventory.getStock() >= quantity) {
                inventory.setStock(inventory.getStock() - quantity);
                inventoryRepository.save(inventory);
                System.out.println("Reduced stock for product ID: " + productId);
                return true; // Success
            }
            return false; // Not enough stock
        }).orElse(false); // Product not found
    }

    // Method to increase stock (for restocking products)
    public void increaseStockForProductId(Long productId, int quantity) {
        inventoryRepository.findByProductId(productId).ifPresent(inventory -> {
            inventory.setStock(inventory.getStock() + quantity);
            inventoryRepository.save(inventory);
            System.out.println("Increased stock for product ID: " + productId);
        });
    }

    // Method to add new stock for a product ID
    public String addStockForProductId(Long productId, int quantity) {
        if (inventoryRepository.existsByProductId(productId)) {
            return "Stock already exists. Use increaseStockForProductId to add more.";
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
        Inventory inventory = new Inventory(product, quantity);
        inventoryRepository.save(inventory);
        System.out.println("Stock added for product ID: " + productId);
        return "Stock Added";
    }
}