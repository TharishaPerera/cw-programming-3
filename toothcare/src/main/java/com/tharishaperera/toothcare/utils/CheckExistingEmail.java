package com.tharishaperera.toothcare.utils;

import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.services.UserService;

public class CheckExistingEmail {
    public static boolean checkEmailExists(String email) {
        for (User user : User.userList) {
            if (email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
