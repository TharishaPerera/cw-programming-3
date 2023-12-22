package com.tharishaperera.toothcare.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public void login (@RequestParam String email, @RequestParam String password) {
        System.out.println(email);
        System.out.println(password);
        System.out.println("login endpoint");
    }
}
