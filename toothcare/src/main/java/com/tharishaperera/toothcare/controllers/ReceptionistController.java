package com.tharishaperera.toothcare.controllers;

import com.tharishaperera.toothcare.models.Receptionist;
import com.tharishaperera.toothcare.models.User;
import com.tharishaperera.toothcare.services.UserService;
import com.tharishaperera.toothcare.utils.CheckExistingEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/receptionists")
@CrossOrigin(origins = "${frontend.allowed-origin}")
public class ReceptionistController {
    @Autowired
    private UserService userService;

    // get all receptionists
    @GetMapping()
    public List<User> getAllReceptionists() {
        return userService.getAllReceptionists();
    }

    // get receptionist by id
    @GetMapping("/{userId}")
    public Optional<Receptionist> getReceptionistById(@PathVariable long userId) {
        return userService.getReceptionistById(userId);
    }

    // create receptionist
    @PostMapping()
    public Receptionist createReceptionist(@RequestBody Receptionist receptionist) {
        if (CheckExistingEmail.checkEmailExists(receptionist.getEmail())) {
            return null;
        }
        Receptionist newReceptionist = Receptionist.createReceptionist(receptionist.getFirstName(), receptionist.getLastName(), receptionist.getEmail(), receptionist.getMobile(), receptionist.getPassword());
        User createdReceptionist = userService.createUser(newReceptionist);
        return (Receptionist) createdReceptionist;
    }

    // update receptionist
    @PutMapping("/{userId}")
    public void updateReceptionist(@PathVariable long userId, @RequestBody Receptionist updatedReceptionist) {
        userService.updateReceptionistById(userId, updatedReceptionist);
    }

    // delete receptionist by id
    @DeleteMapping("/{userId}")
    public void deleteReceptionist(@PathVariable long userId) {
        userService.deleteUserById(userId);
    }
}
