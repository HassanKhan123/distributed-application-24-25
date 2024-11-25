package com.example.task1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productPrice;
    private String productSize;
    private String productColor;

    public Product() {
    }

    public Product(String productName, String productPrice, String productSize, String productColor) {

        this.productName = productName;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productColor = productColor;

    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductSize() {
        return productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

}