package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //Create or save or add
    User createUser(User user);

    //Retrieve or get student on the basis of Primary Key
    Optional<User> getUserById(Integer userId);

    //Update
    User updateUser(Integer userId, User user);

    //Delete
    void deleteUser(Integer userId);

    List<User> getAllUsers();

    Optional<List<Incident>> getAllIncidents(Integer userId);
}