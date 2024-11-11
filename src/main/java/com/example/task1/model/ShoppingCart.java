package com.example.task1.model;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // A map to store products and their quantities
    public Map<Product, Integer> products = new HashMap<>();

    // Add a product to the cart (or update quantity if it exists)
    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    // Remove a product from the cart
    public void removeProduct(Product product) {
        products.remove(product);
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

    // Clear the cart
    public void clear() {
        products.clear();
    }
}
