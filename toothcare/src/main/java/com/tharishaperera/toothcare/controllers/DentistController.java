package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.Dentist;
import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/dentists")
public class DentistController {
    @Autowired
    private UserService userService;

    // get all dentists
    @GetMapping()
    public List<User> getAllDentists() {
        return userService.getAllDentists();
    }

    // get dentist by id
    @GetMapping("/{userId}")
    public Optional<Dentist> getDentistById(@PathVariable long userId) {
        return userService.getDentistById(userId);
    }

    // create dentist
    @PostMapping()
    public Dentist createDentist(@RequestBody Dentist dentist) {
        Dentist newDentist = Dentist.createDentist(dentist.getFirstName(), dentist.getLastName(), dentist.getEmail(), dentist.getMobile(), dentist.getSpecialization(), dentist.getQualification(), dentist.getPassword());
        User createdDentist = userService.createUser(newDentist);
        return (Dentist) createdDentist;
    }

    // update dentist
    @PutMapping("/{userId}")
    public void updateDentist(@PathVariable long userId, @RequestBody Dentist updatedDentist) {
        userService.updateDentistById(userId, updatedDentist);
    }

    // delete dentist by id
    @DeleteMapping("/{userId}")
    public void deleteDentist(@PathVariable long userId) {
        userService.deleteUserById(userId);
    }

}
