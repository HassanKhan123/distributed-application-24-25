package com.example.task1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Inventory;
import com.example.task1.model.Product;
import com.example.task1.repository.InventoryRepository;
import com.example.task1.repository.ProductRepository;

/**
 * Service class for managing inventory operations in the system.
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link InventoryRepository}: Manages inventory-related database
 * operations.</li>
 * <li>{@link ProductRepository}: Provides access to product data for inventory
 * management.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Provides:
 * <ul>
 * <li>CRUD operations for inventory data.</li>
 * <li>Stock management for products, including adding, reducing, and checking
 * stock levels.</li>
 * </ul>
 * </p>
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    /**
     * Constructs a new instance of {@code InventoryService}.
     *
     * @param inventoryRepository the repository for inventory-related operations
     * @param productRepository   the repository for product-related operations
     */
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    /**
     * Retrieves a list of all inventory records.
     *
     * @return a list of all inventory items in the system
     */
    public List<Inventory> getStocks() {
        return inventoryRepository.findAll();
    }

    /**
     * Retrieves the stock count for a specific product ID.
     *
     * @param productId the ID of the product
     * @return the stock count for the specified product, or 0 if not found
     */
    public int getStockForProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .map(Inventory::getStock)
                .orElse(0);
    }

    /**
     * Reduces the stock for a specific product ID by a given quantity.
     *
     * @param productId the ID of the product
     * @param quantity  the quantity to reduce from the stock
     * @return {@code true} if stock was successfully reduced; {@code false} if
     *         insufficient stock or product not found
     */
    public boolean reduceStockForProductId(Long productId, int quantity) {
        return inventoryRepository.findByProductId(productId).map(inventory -> {
            if (inventory.getStock() >= quantity) {
                inventory.setStock(inventory.getStock() - quantity);
                inventoryRepository.save(inventory);
                System.out.println("Reduced stock for product ID: " + productId);
                return true;
            }
            return false;
        }).orElse(false);
    }

    /**
     * Increases the stock for a specific product ID by a given quantity.
     *
     * @param productId the ID of the product
     * @param quantity  the quantity to add to the stock
     */
    public void increaseStockForProductId(Long productId, int quantity) {
        inventoryRepository.findByProductId(productId).ifPresent(inventory -> {
            inventory.setStock(inventory.getStock() + quantity);
            inventoryRepository.save(inventory);
            System.out.println("Increased stock for product ID: " + productId);
        });
    }

    /**
     * Adds new stock for a specific product ID.
     *
     * <p>
     * If the stock already exists, a message is returned recommending the use of
     * {@link #increaseStockForProductId(Long, int)} to add more stock.
     * </p>
     *
     * @param productId the ID of the product
     * @param quantity  the quantity of stock to add
     * @return a success message or an error message if stock already exists
     * @throws IllegalArgumentException if the product with the specified ID does
     *                                  not exist
     */
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
