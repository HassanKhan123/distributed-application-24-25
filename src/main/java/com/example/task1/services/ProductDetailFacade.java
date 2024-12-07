package com.example.task1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Product;
import com.example.task1.model.ProductDetailDTO;

/**
 * Facade class for fetching detailed product information, including stock
 * details.
 * 
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link ProductService}: Used to retrieve product information.</li>
 * <li>{@link InventoryService}: Used to retrieve stock information for
 * products.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Provides:
 * <ul>
 * <li>A consolidated view of product details, including stock information.</li>
 * </ul>
 * </p>
 */
@Service
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;

    /**
     * Constructs a new instance of {@code ProductDetailFacade}.
     *
     * @param productService   the service to handle product-related operations
     * @param inventoryService the service to handle inventory-related operations
     */
    @Autowired
    public ProductDetailFacade(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    /**
     * Fetches detailed product information, including stock availability.
     *
     * @param productId the ID of the product to retrieve details for
     * @return a {@link ProductDetailDTO} containing the product and its stock
     *         information
     */
    public ProductDetailDTO getProductDetail(Long productId) {
        // Retrieve product information using ProductService
        Product product = productService.get(productId);

        // Debug log: Print product information
        System.out.println("ProductDetailDTO");

        // Debug log: Print inventory stock details
        System.out.println(inventoryService.getStocks());

        // Retrieve stock information using InventoryService
        int stock = inventoryService.getStockForProductId(productId);

        // Debug log: Print stock information
        System.out.println("STOCK: " + stock);

        // Return the detailed product information along with stock
        return new ProductDetailDTO(product, stock);
    }
}
