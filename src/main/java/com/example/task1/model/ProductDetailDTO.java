package com.example.task1.model;

public class ProductDetailDTO {
    private Product product; // Product details (from ProductService)
    private int stock; // Stock count (from InventoryService)
    private boolean isSoldOut; // Indicates if the product is sold out

    // Constructor
    public ProductDetailDTO(Product product, int stock) {
        this.product = product;
        this.stock = stock;
        this.isSoldOut = (stock == 0); // If stock is 0, it is sold out
    }

    // Getters and setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        this.isSoldOut = (stock == 0);
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}
