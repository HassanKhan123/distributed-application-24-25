package com.example.task1.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Product;

/**
 * Service class for managing a shopping cart in the system.
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link InventoryService}: Ensures stock availability and updates
 * inventory as products are added to the cart.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Provides:
 * <ul>
 * <li>Adding products to the cart while managing stock availability.</li>
 * <li>Calculating the total value of all items in the cart.</li>
 * <li>Retrieving all items currently in the cart.</li>
 * </ul>
 * </p>
 */
@Service
public class ShoppingCartService {

    /**
     * A map representing the shopping cart.
     * The key is the {@link Product}, and the value is the quantity of that product
     * in the cart.
     */
    private final Map<Product, Integer> products = new HashMap<>();

    private final InventoryService inventoryService;

    /**
     * Constructs a new instance of {@code ShoppingCartService}.
     *
     * @param inventoryService the service used for inventory management
     */
    @Autowired
    public ShoppingCartService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves all items currently in the shopping cart.
     *
     * @return a map containing the products and their quantities in the cart
     */
    public Map<Product, Integer> getAllCartItems() {
        return products;
    }

    /**
     * Adds a product to the shopping cart.
     * 
     * <p>
     * If the product is already in the cart, the quantity is updated.
     * The method also checks the stock availability and reduces the stock in the
     * inventory if the product is added successfully.
     * </p>
     *
     * @param product  the product to add to the cart
     * @param quantity the quantity of the product to add
     * @throws Exception if the product is out of stock or there is an inventory
     *                   error
     */
    public void addProduct(Product product, int quantity) throws Exception {
        try {
            int stockQuantity = inventoryService.getStockForProductId(product.getId());

            if (stockQuantity > 0) {
                products.put(product, products.getOrDefault(product, 0) + quantity);
                inventoryService.reduceStockForProductId(product.getId(), quantity);
            } else {
                throw new Exception("No stock left for product: " + product.getProductName());
            }
        } catch (Exception e) {
            throw new Exception("Error adding product to cart: " + product.getProductName(), e);
        }
    }

    /**
     * Calculates the total value of all items in the shopping cart.
     *
     * @return the total price of all items in the cart
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO; // Initialize total as BigDecimal.ZERO for precision

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Multiply product price by quantity and add to the total
            BigDecimal productPrice = product.getProductPrice(); // Assuming getProductPrice() returns BigDecimal
            BigDecimal productTotal = productPrice.multiply(BigDecimal.valueOf(quantity));
            total = total.add(productTotal);
        }

        return total;
    }
}
