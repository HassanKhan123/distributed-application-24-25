package com.example.task1.model;

/**
 * Data Transfer Object (DTO) representing detailed information about a product.
 * 
 * <p>
 * This DTO combines product details along with stock information and
 * availability status.
 * It is used to transfer data between layers of the application without
 * exposing
 * the internal structure of entities.
 * </p>
 * 
 * <p>
 * Provides:
 * <ul>
 * <li>Details of the product, such as name, price, size, and color.</li>
 * <li>Stock count fetched from the InventoryService.</li>
 * <li>Sold-out status derived from the stock count.</li>
 * </ul>
 * </p>
 */
public class ProductDetailDTO {

    /**
     * The product details, typically fetched from the ProductService.
     */
    private Product product;

    /**
     * The stock count of the product, retrieved from the InventoryService.
     */
    private int stock;

    /**
     * Indicates whether the product is sold out.
     * Set to {@code true} if stock is 0; otherwise, {@code false}.
     */
    private boolean isSoldOut;

    /**
     * Constructor to initialize the ProductDetailDTO with product details and stock
     * count.
     * The sold-out status is derived automatically based on the stock value.
     *
     * @param product the product details
     * @param stock   the stock count of the product
     */
    public ProductDetailDTO(Product product, int stock) {
        this.product = product;
        this.stock = stock;
        this.isSoldOut = (stock == 0); // Automatically calculate sold-out status
    }

    /**
     * Gets the product details.
     *
     * @return the product details
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product details.
     *
     * @param product the product details to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the stock count for the product.
     *
     * @return the stock count
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock count for the product.
     * Automatically updates the sold-out status based on the stock value.
     *
     * @param stock the stock count to set
     */
    public void setStock(int stock) {
        this.stock = stock;
        this.isSoldOut = (stock == 0);
    }

    /**
     * Checks whether the product is sold out.
     *
     * @return {@code true} if the product is sold out, otherwise {@code false}
     */
    public boolean isSoldOut() {
        return isSoldOut;
    }

    /**
     * Sets the sold-out status for the product.
     * 
     * @param soldOut {@code true} if the product is sold out, otherwise
     *                {@code false}
     */
    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}
