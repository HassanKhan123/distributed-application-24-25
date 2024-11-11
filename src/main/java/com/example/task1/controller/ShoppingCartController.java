package com.example.task1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.task1.model.Product;
import com.example.task1.model.ShoppingCart;
import com.example.task1.services.ProductService;
import com.example.task1.services.ShoppingCartService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    // Show the shopping cart
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("shoppingCart", shoppingCartService.getShoppingCart());
        model.addAttribute("cartTotal", shoppingCartService.getCartTotal());
        return "shoppingCart";
    }

    // Add a product to the cart
    @PostMapping("/add")
    public String addProductToCart(@RequestBody ShoppingCart productId) {
        System.out.println("11111111111111"+productId);
        // Product product = productService.get(productId);
        // System.out.println(product);
        // if (product != null) {
        //     shoppingCartService.addProductToCart(product, quantity);
        // }
        return "Successfully added to cart";
    }

    // Remove a product from the cart
    @PostMapping("/remove")
    public String removeProductFromCart(@RequestParam String productId) {
        Product product = productService.get(productId);
        if (product != null) {
            shoppingCartService.removeProductFromCart(product);
        }
        return "redirect:/cart";  // Redirect to the cart page
    }

    // Clear the shopping cart
    @PostMapping("/clear")
    public String clearCart() {
        shoppingCartService.clearCart();
        return "redirect:/cart";  // Redirect to the cart page
    }

    
}
