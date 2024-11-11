package com.example.task1.services;

import org.springframework.stereotype.Service;

import com.example.task1.model.Product;
import com.example.task1.model.ShoppingCart;

@Service
public class ShoppingCartService {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    // Add a product to the shopping cart
    public void addProductToCart(Product product, int quantity) {
        shoppingCart.addProduct(product, quantity);
    }

    // Remove a product from the shopping cart
    public void removeProductFromCart(Product product) {
        shoppingCart.removeProduct(product);
    }

    // Get the shopping cart object (for displaying)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    // Get the total sum of the shopping cart
    public double getCartTotal() {
        return shoppingCart.getTotal();
    }

    // Clear the cart
    public void clearCart() {
        shoppingCart.clear();
    }
}
