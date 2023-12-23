package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.models.Dentist;
import com.tharishaperera.toothcare.models.Receptionist;
import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.interfaces.UserWithPassword;
import com.tharishaperera.toothcare.utils.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    public Optional<User> login(String email, String password) {
        for (User user : User.userList) {
            if (user.getEmail().equals(email) && SecurityConfig.checkPassword(password, ((UserWithPassword) user).getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
