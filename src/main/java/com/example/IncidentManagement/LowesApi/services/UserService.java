package com.example.IncidentManagement.LowesApi.services;

import com.example.IncidentManagement.LowesApi.entity.Incident;
import com.example.IncidentManagement.LowesApi.entity.User;

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