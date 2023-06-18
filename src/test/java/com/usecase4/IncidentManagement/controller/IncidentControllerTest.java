package com.usecase4.IncidentManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usecase4.IncidentManagement.dao.IncidentRepository;
import com.usecase4.IncidentManagement.entity.Enums;
import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.entity.User;
import com.usecase4.IncidentManagement.services.IncidentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = IncidentController.class)
class IncidentControllerTest {
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private IncidentService inciServ;

    @MockBean
    private IncidentRepository inciDao;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetIncidents() throws Exception {
		User user1 = new User(1, "Daniyal", Enums.Department.Marketing, new ArrayList<>(), "xyz@gmail.com", "9457586425");

		Incident incident1 = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
		Incident incident2 = new Incident(19, "Server DRC Up", "lorem ipsum dolor", Enums.Priority.Critical, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
		Incident incident3 = new Incident(20, "Server cracking", "makes wierd sound", Enums.Priority.Low, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

		List<Incident> list = new ArrayList<>();
		list.add(incident1);
		list.add(incident2);
		list.add(incident3);

		when(inciServ.getIncidents()).thenReturn(list);

		RequestBuilder request = MockMvcRequestBuilders.get("/incidents").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();

		String expected = asJsonString(list);
		String actual = result.getResponse().getContentAsString();
		
		assertThat(expected).isEqualTo(actual);
		
	}

	@Test
	void testGetIncident() throws Exception {
        User user1 = new User(1, "Daniyal", Enums.Department.Marketing, new ArrayList<>(), "xyz@gmail.com", "9457586425");
        Incident origData = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

		// Need to convert origData into Optional type. 
		Optional<Incident> incident = Optional.ofNullable(origData);
		Mockito.when(inciServ.getIncident(Mockito.anyLong())).thenReturn(incident);

		RequestBuilder request = MockMvcRequestBuilders.get("/incidents/5").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();

		// We can't pass in incident in argument of asJsonString() because doing string serialization of Optional returns an unwanted result 
		String expectRes = IncidentControllerTest.asJsonString(origData);
		String outputRes = result.getResponse().getContentAsString();

		assertThat(outputRes).isEqualTo(expectRes);

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testCreateIncident() throws Exception {
        User user1 = new User(1, "Gautam", Enums.Department.Marketing, new ArrayList<>(), "xyz@gmail.com", "9457586425");
        Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

		String inputInJSON = asJsonString(incident);

		when(inciServ.createIncident(Mockito.any(Incident.class))).thenReturn(incident);

		RequestBuilder request = MockMvcRequestBuilders.post("/incidents").accept(MediaType.APPLICATION_JSON).content(inputInJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();

		String outputInJSON = result.getResponse().getContentAsString();

		assertThat(outputInJSON).isEqualTo(inputInJSON);

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testUpdateIncident() throws Exception {
        User user1 = new User(1, "Dan", Enums.Department.Marketing, new ArrayList<>(), "xyz@gmail.com", "9457586425");
        Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

		String inputInJSON = asJsonString(incident);

		when(inciServ.createIncident(Mockito.any(Incident.class))).thenReturn(incident);

		RequestBuilder request = MockMvcRequestBuilders.post("/incidents").accept(MediaType.APPLICATION_JSON).content(inputInJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();

		String createdJSON = result.getResponse().getContentAsString();


		incident.setUser(user1);
		incident.setInciPriority(Enums.Priority.Low);
		incident.setInciStatus(Enums.Status.Resolved);

		String updatedInputInJSON = asJsonString(incident);

		when(inciServ.updateIncident(18L, Mockito.any(Incident.class))).thenReturn(incident);

		RequestBuilder request2 = MockMvcRequestBuilders.put("/incidents").accept(MediaType.APPLICATION_JSON).content(updatedInputInJSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result2 = mockMvc.perform(request2).andReturn();

		String outputInJSON = result2.getResponse().getContentAsString();

		assertThat(outputInJSON).isEqualTo(updatedInputInJSON).isNotEqualTo(inputInJSON);

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	//Need to rewrite this test case but in UserControllerTest
//	@Test
//	void testGetIncidentsByUser() throws Exception {
//		User user1 = new User(1, "Dani", Enums.Department.Development, new ArrayList<>());
//		Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
//		Incident incident2 = new Incident(19, "Server DRC Up", "lorem ipsum dolor", Enums.Priority.Critical, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
//		Incident incident3 = new Incident(20, "Server cracking", "makes wierd sound", Enums.Priority.Low, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);
//
//		List<Incident> list = new ArrayList<>();
//		list.add(incident);
//		list.add(incident2);
//		list.add(incident3);
//
//		when(inciDao.findByUserId(Mockito.anyInt())).thenReturn(list);
//
//		RequestBuilder request = MockMvcRequestBuilders.get("/incidents/user/532").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(request).andReturn();
//
//		String expectRes = IncidentControllerTest.asJsonString(list);
//		String outputRes = result.getResponse().getContentAsString();
//
//		assertThat(outputRes).isEqualTo(expectRes);
//
//		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
//	}

	@Test
	void testdeleteIncident() throws Exception {
        User user1 = new User(1, "Daniyal", Enums.Department.Marketing, new ArrayList<>(), "xyz@gmail.com", "9457586425");
        Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1);

//		Mockito.when(inciServ.deleteIncident(Mockito.anyLong())).thenReturn(incident);

		RequestBuilder request = MockMvcRequestBuilders.delete("/incidents/18");
		MvcResult result = mockMvc.perform(request).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

	}
	
    /* Maps and Object to JSON String */
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    
	}
	
}
