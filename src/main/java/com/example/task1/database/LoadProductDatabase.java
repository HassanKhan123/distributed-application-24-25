package com.example.task1.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.task1.model.Inventory;
import com.example.task1.model.Product;
import com.example.task1.repository.InventoryRepository;
import com.example.task1.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;

@Configuration
public class LoadProductDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        return args -> {
            // Check if both tables are empty
            if (productRepository.count() == 0 && inventoryRepository.count() == 0) {
                // Create and save hardcoded products and initialize inventory with stock 100
                log.info("Preloading " + productRepository.save(new Product("Shirt", "50", "L", "Black")));
                Product shirt = productRepository.findById(1L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(shirt, 100));

                log.info("Preloading " + productRepository.save(new Product("Jeans", "150", "M", "Black")));
                Product jeans = productRepository.findById(2L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(jeans, 100));

                log.info("Preloading " + productRepository.save(new Product("Jacket", "350", "XL", "Blue")));
                Product jacket = productRepository.findById(3L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(jacket, 100));

                log.info("Preloading " + productRepository.save(new Product("Sweater", "250", "L", "Green")));
                Product sweater = productRepository.findById(4L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(sweater, 100));

                log.info("Preloading " + productRepository.save(new Product("T-Shirt", "50", "S", "Red")));
                Product tshirt = productRepository.findById(5L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(tshirt, 100));

                log.info("Preloading " + productRepository.save(new Product("Coat", "450", "L", "Gray")));
                Product coat = productRepository.findById(6L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(coat, 100));

                log.info("Preloading " + productRepository.save(new Product("Shoes", "250", "L", "White")));
                Product shoes = productRepository.findById(7L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(shoes, 100));

                log.info("Preloading " + productRepository.save(new Product("Hoodie", "50", "S", "Blue")));
                Product hoodie = productRepository.findById(8L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(hoodie, 100));

                log.info("Preloading " + productRepository.save(new Product("Sleeveless Hoodie", "50", "L", "Green")));
                Product sleeveLessHoodie = productRepository.findById(9L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(sleeveLessHoodie, 100));

                log.info("Preloading " + productRepository.save(new Product("Black Shirt", "50", "L", "Black")));
                Product blackShirt = productRepository.findById(10L).orElseThrow(); // Fetch product by ID
                inventoryRepository.save(new Inventory(blackShirt, 100));
            } else {
                log.info("Products and Inventory are already initialized. Skipping preload.");
            }
        };
    }
}