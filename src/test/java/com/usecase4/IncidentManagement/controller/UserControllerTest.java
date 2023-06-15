package com.usecase4.IncidentManagement.controller;

import com.usecase4.IncidentManagement.entity.Enums;
import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;
import com.usecase4.IncidentManagement.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user1 = new User(1, "daniyal", Enums.Department.HR, new ArrayList<>(), "xyz@gmail.com", "9457586425");
        User user2 = new User(2, "Mahmood", Enums.Department.HR, new ArrayList<>(), "xyz@gmail.com", "9457586425");

        Incident incident1 = new Incident(2, "Issue 1", "description 1", Enums.Priority.Critical, Enums.Status.New, Enums.Category.Accessory_Issues, user1);
        Incident incident2 = new Incident(5, "Issue 2", "description 2", Enums.Priority.Critical, Enums.Status.New, Enums.Category.Accessory_Issues, user1);
        Incident incident3 = new Incident(8, "Issue 3", "description 3", Enums.Priority.Critical, Enums.Status.New, Enums.Category.Accessory_Issues, user1);

        user1.setIncidents(Arrays.asList(incident1, incident2));
        user2.setIncidents(Collections.singletonList(incident3));

        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }


    @Test
    public void testUpdateUser() {
        // Arrange
        Integer userId = 1;
        User existingUser = new User(1, "daniyal", Enums.Department.HR, new ArrayList<>(), "xyz@gmail.com", "9457586425");
        User updatedUser = new User(1, "Mahmood", Enums.Department.HR, new ArrayList<>(), "xyz@gmail.com", "9457586425");

        when(userService.getUserById(userId)).thenReturn(existingUser);
        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(updatedUser);

        // Act
        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    public void testGetIncidentsByUserId() {
        // Mock user ID
        Integer userId = 1;

        // Create a list of incidents
        List<Incident> incidents = new ArrayList<>();
        // Add incidents to the list
        Incident incident1 = new Incident(1, "Incident1", "Description 1", Enums.Priority.Critical, Enums.Status.New, Enums.Category.Accessory_Issues, null);
        Incident incident2 = new Incident(2, "incident 2", "Description 2", Enums.Priority.Low, Enums.Status.New, Enums.Category.Accessory_Issues, null);
        incidents.add(incident1);
        incidents.add(incident2);

        // Mock the userService to return the list of incidents
        Mockito.lenient().when(userService.getAllIncidents(userId)).thenReturn(Optional.of(incidents));

        // Call the controller method
        ResponseEntity<List<Incident>> response = userController.getIncidentsByUserId(userId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(incidents, response.getBody());
    }
//    }

//    @Test
//    public void testDeleteUser() {
//        // Arrange
//        Integer userId = 1;
//        when(userService.deleteUser(userId)).thenCallRealMethod();
//
//        // Act
//        ResponseEntity<String> response = userController.deleteUserById(userId);
//
//        // Assert
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        assertEquals("User deleted successfully", response.getBody());
//    }
}