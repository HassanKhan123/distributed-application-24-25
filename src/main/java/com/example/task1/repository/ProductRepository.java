package com.example.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.task1.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // // Additional custom queries can be added here if needed.
    List<Product> findByProductColorIgnoreCase(String productColor);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(:productName%)")
    List<Product> searchByProductNameStartingWith(String productName);

    // List<Product> findBySizeIgnoreCase(String productSize);
}
