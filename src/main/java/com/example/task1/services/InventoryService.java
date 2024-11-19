package com.example.task1.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    // Map to store stock count for each product ID, initialized once.
    private final Map<String, Integer> inventory;

    public InventoryService() {

        inventory = new HashMap<>();
        inventory.put("1", 100);
        inventory.put("2", 100);
        inventory.put("3", 100);
        inventory.put("4", 100);
        inventory.put("5", 100);
    }

    // Method to get the stock count for a product ID
    public Collection getStocks() {
        System.out.println("Current inventory keys: " + inventory.keySet());
        return inventory.values(); // Default to 0 if no stock is found
    }

    // Method to get the stock count for a product ID
    public int getStockForProductId(String productId) {

        System.out.println("Current inventory keys: " + inventory.keySet());
        return inventory.get(productId); // Default to 0 if no stock is found
    }

    // Method to reduce the stock for a product ID
    public boolean reduceStockForProductId(String productId, int quantity) {
        int currentStock = getStockForProductId(productId);
        if (currentStock >= quantity) {
            inventory.put(productId, currentStock - quantity); // Reduce stock
            System.out.println("Updated inventory values: " + inventory.values());
            return true; // Success
        }
        return false; // Not enough stock to reduce
    }

    // Method to increase stock (optional, for restocking products)
    public void increaseStockForProductId(String productId, int quantity) {
        inventory.put(productId, getStockForProductId(productId) + quantity);
    }

    // Method to add new stock for a product ID
    public String addStockForProductId(String productId, int quantity) {
        if (inventory.containsKey(productId)) {
            return "Product already exists. Use increaseStockForProductId to add more.";
        }
        System.out.println("Adding new stock for product ID: " + productId);
        inventory.put(productId, quantity);
        System.out.println("Updated inventory keys after adding: " + inventory.keySet());
        return "Stock Added";
    }
}
