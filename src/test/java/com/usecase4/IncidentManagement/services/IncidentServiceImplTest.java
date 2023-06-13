package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.dao.IncidentDao;
import com.usecase4.IncidentManagement.entity.Incident;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class IncidentServiceImplTest {

	@MockBean
	private IncidentDao inciDao;

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
		Incident incident1 = new Incident(17, "Server RX down", "abcdefgh", "High", "Hardware Issues", "New", 550, "Neha", "IT");
		Incident incident2 = new Incident(18, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		
		java.util.List<Incident> list = new ArrayList<>();
		list.add(incident1);
		list.add(incident2);
		
		when(inciDao.findAll()).thenReturn(list);
		
		assertThat(inciservice.getIncidents()).isEqualTo(list);
		
	}
	
	@Test
	void testGetIncident() {
		
		Optional<Incident> incident = Optional.of(new Incident(101, "CPU heated", "abcdefgh", "High", "Hardware Issues", "New", 550, "Neha", "IT"));
		long id = incident.get().getId();
		System.out.println("id = " + id);

		// Even Though the function getIncident returns the Incident created we are defining what
		// .findById() returns for better code comprehension
		when(inciDao.findById(id)).thenReturn(incident);

		assertThat(inciservice.getIncident(id)).isEqualTo(incident);
	}
	
	@Test
	void createIncidentTest()
	{
		Incident incident = new Incident(122, "CPU heated", "abcdefgh", "High", "Hardware Issues", "New", 550, "Neha", "IT");
		
		// Even Though the function createIncident returns the Incident created we are defining what 
		// .save() returns for better code comprehension
		when(inciDao.save(incident)).thenReturn(incident);
		
		assertThat(inciservice.createIncident(incident)).isEqualTo(incident);
	}
	
	@Test
	void updateIncidentTest()
	{
//		Original incident that remains untouched to be compared
		Incident origInci = new Incident(800, "System 331 Not working", "abcdefgh", "High", "Hardware Issues", "New", 550, "Neha", "IT");
		
//		Incident to be Updated
		Incident actualInci = new Incident(800, "System 331 Not working", "abcdefgh", "High", "Hardware Issues", "New", 550, "Neha", "IT");
		
		inciDao.save(origInci);
		
		actualInci.setInciPriority("Low");
		actualInci.setUserName("Chetna");
		
		
		assertThat(inciservice.updateIncident(actualInci)).isEqualTo(actualInci).isNotEqualTo(origInci);
	}
	
	@Test
	void deleteIncidentTest()
	{
		Incident incident = new Incident(800, "System 331 Not working", "abcdefgh", "High", "Hardware Issues", "New", 550, "Neha", "IT");
		inciDao.save(incident);
		
		inciservice.deleteIncident(incident.getId());
		
		assertThat(inciDao.findById(incident.getId())).isEmpty();
	}

}
