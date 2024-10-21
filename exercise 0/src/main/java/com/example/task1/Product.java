package com.example.task1;

public class Product {
    private final String id;
    private final String name;
    private final String price;
    private final String size;
    private final String color;




    public Product(String id, String name, String price, String size, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

   
}