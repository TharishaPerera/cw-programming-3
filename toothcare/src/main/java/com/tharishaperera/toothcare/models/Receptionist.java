package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.UserType;
import com.tharishaperera.toothcare.interfaces.UserWithPassword;
import com.tharishaperera.toothcare.utils.SecurityConfig;
import com.tharishaperera.toothcare.utils.Utils;

public class Receptionist extends User implements UserWithPassword{
    private String password;

    public Receptionist(long userId, String firstName, String lastName, String email, long mobile, UserType userType, String password) {
        super(userId, firstName, lastName, email, mobile, userType);
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static Receptionist createReceptionist(String firstName, String lastName, String email, long mobile, String password) {
        long userId = Utils.generateId();
        UserType userType = UserType.RECEPTIONIST;
        String hashedPassword = SecurityConfig.hashPassword(password);
        return new Receptionist(userId,firstName,lastName,email,mobile,userType,hashedPassword);
    }
}
