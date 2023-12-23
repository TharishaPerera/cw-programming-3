package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "${frontend.allowed-origin}")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public Optional<User> login (@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }
}
