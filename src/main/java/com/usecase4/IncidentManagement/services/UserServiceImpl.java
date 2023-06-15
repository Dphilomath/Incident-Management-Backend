package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.dao.UserRepository;
import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;
import com.usecase4.IncidentManagement.services.util.IterableToList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        if (userRepo.existsById(userId))
            return userRepo.findById(userId).get();
        else throw new NoSuchElementException();
    }

    @Override
    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserName(updatedUser.getUserName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setDepartment(updatedUser.getDepartment());
            return userRepo.save(user);
        }
        throw new NoSuchElementException("No user with userId:" + userId + " exists");
    }

    @Override
    public boolean deleteUser(Integer userId) throws NoSuchElementException {
        if (userRepo.existsById(userId))
            userRepo.deleteById(userId);
        else throw new NoSuchElementException();
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> users = userRepo.findAll();
        return new ArrayList<>(IterableToList.makeCollection(users));
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public Optional<List<Incident>> getAllIncidents(Integer userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        return userOptional.map(User::getIncidents);
    }
}