package com.tharishaperera.toothcare.models;

import com.tharishaperera.toothcare.config.enums.UserType;
import com.tharishaperera.toothcare.utils.SecurityConfig;
import com.tharishaperera.toothcare.utils.Utils;

public class Dentist extends User implements UserWithPassword {
    private String specialization;
    private String qualification;
    private String password;

    public Dentist(long userId, String firstName, String lastName, String email, long mobile, UserType userType, String specialization, String qualification, String password) {
        super(userId, firstName, lastName, email, mobile, userType);
        this.specialization = specialization;
        this.qualification = qualification;
        this.password = password;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static Dentist createDentist(String firstName, String lastName, String email, long mobile, String specialization, String qualification, String password){
        long userId = Utils.generateId();
        UserType userType = UserType.DENTIST;
        String hashedPassword = SecurityConfig.hashPassword(password);
        return new Dentist(userId,firstName,lastName,email,mobile,userType,specialization,qualification,hashedPassword);
    }
}
