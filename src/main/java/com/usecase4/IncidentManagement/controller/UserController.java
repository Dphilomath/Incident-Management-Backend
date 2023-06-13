package com.usecase4.IncidentManagement.controller;

import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;
import com.usecase4.IncidentManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Create or save or add
    //http://localhost:8080/addstudent
    @PostMapping("/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //Retrieve or get student on the basis of Primary Key
    //http://localhost:8080/getstudentbyid/1
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId) {
        return userService.getUserById(userId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/incidents/{id}")
    public ResponseEntity<List<Incident>> getIncidentsByUserId(@PathVariable("id") Integer userId) {
        Optional<List<Incident>> incidentsOptional = userService.getAllIncidents(userId);
        List<Incident> incidents = incidentsOptional.orElse(Collections.emptyList());
        return ResponseEntity.ok(incidents);
    }


    @PutMapping("/user/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(204).body("User deleted successfully");
    }
}