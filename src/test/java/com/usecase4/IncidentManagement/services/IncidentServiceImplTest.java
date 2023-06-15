package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.dao.IncidentRepository;
import com.usecase4.IncidentManagement.entity.Enums;
import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class IncidentServiceImplTest {

    @MockBean
    private IncidentRepository inciDao;

	@Autowired
	private IncidentService inciservice;

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllIncident() {
//		fail("Not yet implemented");

//		inciservice.getIncidents();
//		verify(inciDao).findAll();
		User user1 = new User(1, "Daniyal", Enums.Department.Development, new ArrayList<>(), "xyz@gmail.com", "9457586425");

		Incident incident1 = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
		Incident incident2 = new Incident(18, "DRC down", "random description 2", Enums.Priority.Critical, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

		java.util.List<Incident> list = new ArrayList<>();
		list.add(incident1);
		list.add(incident2);

		when(inciDao.findAll()).thenReturn(list);

		assertThat(inciservice.getIncidents()).isEqualTo(list);

	}
	
	@Test
	void testGetIncident() {
		User user1 = new User(1, "Daniyal", Enums.Department.Development, new ArrayList<>(), "xyz@gmail.com", "9457586425");

		Incident incident1 = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

		Optional<Incident> incident = Optional.of(incident1);
		long id = incident.get().getInciId();
		System.out.println("id = " + id);

		// Even Though the function getIncident returns the Incident created we are defining what
		// .findById() returns for better code comprehension
		when(inciDao.findById(id)).thenReturn(incident);

		assertThat(inciservice.getIncident(id)).isEqualTo(incident);
	}

	@Test
	void createIncidentTest() {
		User user1 = new User(1, "Daniyal", Enums.Department.Development, new ArrayList<>(), "xyz@gmail.com", "9457586425");

		Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);


		// Even Though the function createIncident returns the Incident created we are defining what 
		// .save() returns for better code comprehension
		when(inciDao.save(incident)).thenReturn(incident);

		assertThat(inciservice.createIncident(incident)).isEqualTo(incident);
	}

	@Test
	public void testUpdateIncident_Success() {
		// Create a mock incident
		Long incidentId = 1L;
		Incident existingIncident = new Incident();
		existingIncident.setInciId(Math.toIntExact(incidentId));
		existingIncident.setDescription("Existing Description");
		existingIncident.setInciStatus(Enums.Status.Rejected);
		existingIncident.setInciPriority(Enums.Priority.High);
		existingIncident.setInciCategory(Enums.Category.Software_Issues);

		// Create a mock updated incident
		Incident updatedIncident = new Incident();
		updatedIncident.setDescription("Updated Description");
		updatedIncident.setInciStatus(Enums.Status.Resolved);


		// Mock the findById method of the IncidentRepository to return the existing incident
		when(inciDao.findById(incidentId)).thenReturn(Optional.of(existingIncident));

		// Mock the save method of the IncidentRepository to return the updated incident
		when(inciDao.save(existingIncident)).thenReturn(existingIncident);

		// Call the updateIncident method
		Incident result = inciservice.updateIncident(incidentId, updatedIncident);

		// Verify the behavior
		verify(inciDao, times(1)).findById(incidentId);
		verify(inciDao, times(1)).save(existingIncident);
		assertEquals(updatedIncident.getDescription(), result.getDescription());
		assertEquals(updatedIncident.getInciStatus(), result.getInciStatus());
		// Verify other updated fields as needed
	}

	@Test
	public void testUpdateIncident_IncidentNotFound() {
		// Create a mock incident ID
		Long incidentId = 1L;

		// Create a mock updated incident
		Incident updatedIncident = new Incident();
		updatedIncident.setDescription("Updated Description");
		updatedIncident.setInciStatus(Enums.Status.In_Progress);
		// Set other updated fields as needed

		// Mock the findById method of the IncidentRepository to return an empty optional
		when(inciDao.findById(incidentId)).thenReturn(Optional.empty());

		// Use assertThrows to handle the exception
		assertThrows(NoSuchElementException.class, () -> inciservice.updateIncident(incidentId, updatedIncident));

		// Verify that the findById method was called once
		verify(inciDao, times(1)).findById(incidentId);
		verify(inciDao, never()).save(any());
	}

//	@Test
//	void updateIncidentTest() {
////		Original incident that remains untouched to be compared
//		User user1 = new User(1, "Daniyal", Enums.Department.Development, new ArrayList<>(), "xyz@gmail.com", "9457586425");
//
//		Incident origInci = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
//
////		Incident to be Updated
//		Incident actualInci = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
//
//		inciDao.save(origInci);
//
//		User user2 = new User(1, "Chetna", Enums.Department.HR, new ArrayList<>(), "xyz@gmail.com", "9457586425");
//		actualInci.setInciPriority(Enums.Priority.Low);
//		actualInci.setUser(user2);
//
//
//		assertThat(inciservice.updateIncident(18L, actualInci)).isEqualTo(actualInci).isNotEqualTo(origInci);
//	}

	@Test
	void deleteIncidentTest() {
		User user1 = new User(1, "Daniyal", Enums.Department.Development, new ArrayList<>(), "xyz@gmail.com", "9457586425");

		Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
		inciDao.save(incident);

		inciservice.deleteIncident(incident.getInciId());

		assertThat(inciDao.findById(Integer.toUnsignedLong(incident.getInciId())).isEmpty());
	}

}
