package com.example.task1.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.task1.model.User;
import com.example.task1.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        Collection<User> users = userService.get();
        model.addAttribute("users", users);
        return "users"; // This maps to src/main/resources/templates/users.html
    }

    @GetMapping("/demo/view")
    public String showViewPage(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "view.html"; // Corresponds to src/main/resources/templates/demo/view.html
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute User user, Model model) {
        userService.createUser(user.getName(), user.getEmail());
        model.addAttribute("message", "User created successfully!");
        return "view.html"; // Ensure this template exists in templates

    }

}
