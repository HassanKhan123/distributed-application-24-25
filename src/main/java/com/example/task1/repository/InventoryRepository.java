package com.example.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task1.model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(Long productId);

    boolean existsByProductId(Long productId);
}