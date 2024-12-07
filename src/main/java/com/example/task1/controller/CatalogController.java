package com.example.task1.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.task1.model.Product;
import com.example.task1.repository.ProductRepository;

@Controller
public class CatalogController {

    private final ProductRepository productRepository;

    public CatalogController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/catalog-paginated")
    public String getPaginatedCatalog(@PageableDefault(size = 3) Pageable pageable, Model model) {
        Page<Product> productPage = productRepository.findAll(pageable);
        model.addAttribute("productPage", productPage);
        return "catalog";
    }

    @GetMapping("/product-detail")
    public String getProductDetail(@RequestParam("id") Long productId, Model model) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        model.addAttribute("product", product);
        return "product-detail";
    }
}
