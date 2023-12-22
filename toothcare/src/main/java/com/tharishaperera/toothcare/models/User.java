package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final List<User> userList = new ArrayList<>();
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private UserType userType;

    public User(long userId, String firstName, String lastName, String email, long mobile, UserType userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.userType = userType;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getMobile() {
        return mobile;
    }
    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
