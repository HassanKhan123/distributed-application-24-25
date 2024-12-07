package com.example.task1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Product;
import com.example.task1.repository.InventoryRepository;
import com.example.task1.repository.ProductRepository;

import java.util.List;
import java.util.Collection;

/**
 * Service class for managing products in the system.
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link InventoryService}: Handles inventory-related operations.</li>
 * <li>{@link ProductRepository}: Provides CRUD operations for products.</li>
 * <li>{@link InventoryRepository}: Manages inventory data for products.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Provides:
 * <ul>
 * <li>CRUD operations for products.</li>
 * <li>Filtering and searching products based on specific criteria.</li>
 * <li>Integration with inventory management for adding and removing
 * products.</li>
 * </ul>
 * </p>
 */
@Service
public class ProductService {

    private final InventoryService inventoryService;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    /**
     * Constructs a new instance of {@code ProductService}.
     *
     * @param inventoryService    the service to handle inventory operations
     * @param productRepository   the repository to manage product data
     * @param inventoryRepository the repository to manage inventory data
     */
    @Autowired
    public ProductService(InventoryService inventoryService, ProductRepository productRepository,
            InventoryRepository inventoryRepository) {
        this.inventoryService = inventoryService;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Retrieves all products in the system.
     *
     * @return a collection of all products
     */
    public Collection<Product> get() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID, or {@code null} if not found
     */
    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Removes a product by its ID.
     *
     * @param id the ID of the product to remove
     */
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Filters products based on their color (case-insensitive).
     *
     * @param color the color to filter products by
     * @return a list of products matching the specified color
     */
    public List<Product> filterProductsByColor(String color) {
        System.out.println("Filtering by color: " + color); // Debug
        List<Product> filteredProducts = productRepository.findByProductColorIgnoreCase(color);
        System.out.println("Filtered products: " + filteredProducts); // Debug
        return filteredProducts;
    }

    /**
     * Searches for products whose names start with the specified string.
     *
     * @param name the prefix to search for in product names
     * @return a list of products whose names start with the given prefix
     */
    public List<Product> searchProductsByName(String name) {
        return productRepository.searchByProductNameStartingWith(name);
    }

    /**
     * Adds a new product to the system and initializes its stock in the inventory.
     *
     * @param product the product to add
     * @return the added product with its generated ID
     */
    public Product addProduct(Product product) {
        Product newProduct = productRepository.save(product);
        inventoryService.addStockForProductId(newProduct.getId(), 2);
        return product;
    }

    /**
     * Deletes a product by its ID, along with its inventory data.
     *
     * @param id the ID of the product to delete
     * @return a success message indicating the product has been deleted
     */
    public String deleteProduct(Long id) {
        inventoryRepository.findByProductId(id)
                .ifPresent(inventory -> inventoryRepository.delete(inventory));

        productRepository.deleteById(id);

        return "Product deleted successfully.";
    }

    /**
     * Updates an existing product in the system.
     *
     * @param product the updated product details
     * @return a success message indicating the product has been updated
     */
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "Product updated successfully.";
    }
}
