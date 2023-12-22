package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.UserType;
import com.tharishaperera.toothcare.utils.Utils;

import java.time.LocalDate;

public class Patient extends User{
    private String address;
    private LocalDate dob;

    public Patient(long userId, String firstName, String lastName, String email, long mobile, UserType userType, String address, LocalDate dob) {
        super(userId, firstName, lastName, email, mobile, userType);
        this.address = address;
        this.dob = dob;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // create patient
    public static Patient createPatient(String firstName, String lastName, String email, long mobile, String address, LocalDate dob) {
        long userId = Utils.generateId();
        UserType userType = UserType.PATIENT;
        return new Patient(userId,firstName,lastName,email,mobile,userType,address,dob);
    }
}
