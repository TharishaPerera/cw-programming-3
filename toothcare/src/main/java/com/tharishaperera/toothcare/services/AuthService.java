package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.models.Dentist;
import com.tharishaperera.toothcare.models.Receptionist;
import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.models.UserWithPassword;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    public Optional<User> login(String email, String password) {
        return UserService.userList.stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user instanceof Dentist || user instanceof Receptionist)
                .filter(user -> ((UserWithPassword) user).getPassword().equals(password))
                .findFirst();
    }
}
