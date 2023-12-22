package com.tharishaperera.toothcare.utils;

import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.services.UserService;

public class CheckExistingEmail {
    public static boolean checkEmailExists(String email) {
        for (User user : UserService.userList) {
            if (email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
