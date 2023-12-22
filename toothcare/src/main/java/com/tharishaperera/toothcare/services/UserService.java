package com.tharishaperera.toothcare.services;

import com.tharishaperera.toothcare.config.enums.UserType;
import com.tharishaperera.toothcare.models.Dentist;
import com.tharishaperera.toothcare.models.Receptionist;
import com.tharishaperera.toothcare.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final List<User> userList = new ArrayList<>();

    // get all users
    public List<User> getAllUsers() {
        return userList;
    }

    // create user
    public User createUser(User user) {
        userList.add(user);
        return user;
    }

    // get receptionists
    public List<User> getAllReceptionists() {
        return getUsersByUserType(UserType.RECEPTIONIST);
    }

    // get dentists
    public List<User> getAllDentists() {
        return getUsersByUserType(UserType.DENTIST);
    }

    // get user by user id
    public Optional<User> getUserById(long userId) {
        return userList.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst();
    }

    //get receptionist by id
    public Optional<Receptionist> getReceptionistById(long userId) {
        return userList.stream()
                .filter(user -> user instanceof Receptionist && user.getUserId() == userId)
                .map(user -> (Receptionist) user)
                .findFirst();
    }

    //get dentist by id
    public Optional<Dentist> getDentistById(long userId) {
        return userList.stream()
                .filter(user -> user instanceof Dentist && user.getUserId() == userId)
                .map(user -> (Dentist) user)
                .findFirst();
    }

    // get users by userType
    public List<User> getUsersByUserType(UserType userType) {
        return userList.stream().filter(user -> user.getUserType().equals(userType)).collect(Collectors.toList());
    }

    // update receptionist by id
    public void updateReceptionistById(long userId, Receptionist updatedReceptionist) {
        Optional<User> receptionistToUpdate = userList.stream()
                .filter(user -> user instanceof Receptionist && user.getUserId() == userId)
                .findFirst();

        receptionistToUpdate.ifPresent(user -> {
            Receptionist receptionist = (Receptionist) user;
            receptionist.setFirstName(updatedReceptionist.getFirstName());
            receptionist.setLastName(updatedReceptionist.getLastName());
            receptionist.setEmail(updatedReceptionist.getEmail());
            receptionist.setMobile(updatedReceptionist.getMobile());
            receptionist.setPassword(updatedReceptionist.getPassword());
        });
    }

    // update dentist by id
    public void updateDentistById(long userId, Dentist updatedDentist) {
        Optional<User> dentistToUpdate = userList.stream()
                .filter(user -> user instanceof Dentist && user.getUserId() == userId)
                .findFirst();

        dentistToUpdate.ifPresent(user -> {
            Dentist dentist = (Dentist) user;
            dentist.setFirstName(updatedDentist.getFirstName());
            dentist.setLastName(updatedDentist.getLastName());
            dentist.setEmail(updatedDentist.getEmail());
            dentist.setMobile(updatedDentist.getMobile());
            dentist.setSpecialization(updatedDentist.getSpecialization());
            dentist.setQualification(updatedDentist.getQualification());
            dentist.setPassword(updatedDentist.getPassword());
        });
    }

    // delete user by id
    public void deleteUserById(long userId) {
        userList.removeIf(user -> user.getUserId() == userId);
    }
}
