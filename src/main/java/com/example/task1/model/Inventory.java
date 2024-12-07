package com.example.task1.model;

import jakarta.persistence.*;

/**
 * Represents the inventory for a specific product in the system.
 * 
 * <p>
 * This entity maps to the "inventory" table in the database and contains
 * information about the stock count of a product. It maintains a one-to-one
 * relationship with the {@link Product} entity, ensuring that each product
 * corresponds to exactly one inventory record.
 * </p>
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    /**
     * Unique identifier for the inventory record.
     * Auto-generated primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The product associated with this inventory record.
     * Uses a one-to-one relationship with cascading operations for deletion.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * The number of units in stock for the associated product.
     */
    private int stock;

    /**
     * Default constructor for JPA.
     */
    public Inventory() {
    }

    /**
     * Constructor to initialize an inventory record with a product and its stock
     * count.
     *
     * @param product the product associated with this inventory record
     * @param stock   the stock count for the product
     */
    public Inventory(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    /**
     * Gets the unique identifier for this inventory record.
     *
     * @return the unique identifier (ID)
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this inventory record.
     *
     * @param id the unique identifier (ID) to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the product associated with this inventory record.
     *
     * @return the associated product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with this inventory record.
     *
     * @param product the product to associate
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the current stock count for the associated product.
     *
     * @return the stock count
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock count for the associated product.
     *
     * @param stock the stock count to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
