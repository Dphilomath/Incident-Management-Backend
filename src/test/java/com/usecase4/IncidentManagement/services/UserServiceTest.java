package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.dao.UserRepository;
import com.usecase4.IncidentManagement.entity.Enums;
import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() {
        // Create a mock User object
        User user = new User(1, "john", Enums.Department.HR, null);

        // Mock the save method of UserRepository to return the mock User object
        when(userRepository.save(user)).thenReturn(user);

        // Call the createUser method
        User createdUser = userService.createUser(user);

        // Verify the behavior
        verify(userRepository, times(1)).save(user);
        assertEquals(user, createdUser);
    }

    @Test
    public void testGetIncidentsByUserId() {
        // Mock user ID
        Integer userId = 1;

        User user = new User(userId, "John Doe", Enums.Department.HR, null);

        // Create a user with incidents
        List<Incident> incidents = new ArrayList<>();
        Incident incident1 = new Incident(1, "Incident 1", "Description 1", Enums.Priority.Low, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user);
        Incident incident2 = new Incident(2, "Incident 2", "Description 2", Enums.Priority.High, Enums.Status.Resolved, Enums.Category.Software_Issues, user);
        incidents.add(incident1);
        incidents.add(incident2);

        user.setIncidents(incidents);


        // Mock the user repository to return the user with incidents
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Call the userService method
        Optional<List<Incident>> retrievedIncidents = userService.getAllIncidents(userId);

        // Verify the incidents
        assertEquals(incidents.size(), retrievedIncidents.get().size());
        assertTrue(retrievedIncidents.get().contains(incident1));
        assertTrue(retrievedIncidents.get().contains(incident2));
    }

    @Test
    public void testGetAllUsers() {
        // Create a list of users
        List<User> users = new ArrayList<>();

        users.add(new User(1, "John Doe", Enums.Department.HR, null));
        users.add(new User(5, "Dig Doe", Enums.Department.HR, null));

        // Mock the userRepository to return the list of users
        when(userRepository.findAll()).thenReturn(users);

        // Call the userService method
        List<User> retrievedUsers = userService.getAllUsers();

        // Verify the users
        assertEquals(users.size(), retrievedUsers.size());
        assertEquals(users.get(0), retrievedUsers.get(0));
        assertEquals(users.get(1), retrievedUsers.get(1));
    }

    @Test
    public void testDeleteUser() {
        // Mock user ID
        Integer userId = 1;


        // Call the userService method
        boolean result = userService.deleteUser(userId);

        // Verify the result
        assertTrue(result);
        verify(userRepository, times(1)).deleteById(userId);
    }
}

