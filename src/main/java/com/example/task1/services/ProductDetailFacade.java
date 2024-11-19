package com.example.task1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task1.model.Product;
import com.example.task1.model.ProductDetailDTO;

@Service
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;

    @Autowired
    public ProductDetailFacade(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    // Method to fetch product details along with stock info
    public ProductDetailDTO getProductDetail(String productId) {
        Product product = productService.get(productId); // Get product info from ProductService
        System.out.println("ProductDetailDTO");
        System.out.println(inventoryService.getStocks());
        int stock = inventoryService.getStockForProductId(productId); // Get stock info from InventoryService

        System.out.println("STOCK" + stock);

        return new ProductDetailDTO(product, stock);
    }
}
