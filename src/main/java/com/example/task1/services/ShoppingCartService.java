package com.example.task1.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Product;

@Service
public class ShoppingCartService {

    public Map<Product, Integer> products = new HashMap<>();

    private final InventoryService inventoryService;

    @Autowired
    public ShoppingCartService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public Map<Product, Integer> getAllCartItems() {
        return products;
    }

    // Add a product to the cart (or update quantity if it exists)
    public void addProduct(Product product, int quantity) throws Exception {
        try {

            int stockQuantity = inventoryService.getStockForProductId(product.getId());

            if (stockQuantity > 0) {
                products.put(product, products.getOrDefault(product, 0) + quantity);

                inventoryService.reduceStockForProductId(product.getId(), 1);
            } else {
                throw new Exception("No stock left for product ID: " + product.getName());
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception("No stock left for product ID: " + product.getName());
        }
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
