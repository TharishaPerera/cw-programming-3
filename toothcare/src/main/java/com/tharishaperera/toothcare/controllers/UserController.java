package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "${frontend.allowed-origin}")
public class UserController {
    @Autowired
    private UserService userService;

    // get all users
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
