package com.example.IncidentManagement.LowesApi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.IncidentManagement.LowesApi.dao.IncidentDao;
import com.example.IncidentManagement.LowesApi.entity.Incident;
import com.example.IncidentManagement.LowesApi.services.InciService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value=Controller.class)

class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private InciService inciServ;
	
	@MockBean
	private IncidentDao inciDao;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetIncidents() throws Exception {
		
		Incident incident = new Incident(18, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		Incident incident2 = new Incident(19, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 544, "Neha", "IT");
		Incident incident3 = new Incident(20, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 1000, "Neha", "IT");
		
		List<Incident> list = new ArrayList<>();
		list.add(incident);
		list.add(incident2);
		list.add(incident3);
		
		when(inciServ.getIncidents()).thenReturn(list);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/incidents").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request).andReturn();
		
		String expected = this.asJsonString(list);
		String actual = result.getResponse().getContentAsString();
		
		assertThat(expected).isEqualTo(actual);
		
	}

	@Test
	void testGetIncident() throws Exception {
		
		Incident origData = new Incident(5, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		
		// Need to convert origData into Optional type. 
		Optional<Incident> incident = Optional.ofNullable(origData);
		Mockito.when(inciServ.getincident(Mockito.anyLong())).thenReturn(incident);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/incidents/5").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request).andReturn();
		
		// We can't pass in incident in argument of asJsonString() because doing string serialization of Optional returns an unwanted result 
		String expectRes = ControllerTest.asJsonString(origData);
		String outputRes = result.getResponse().getContentAsString();
		
		assertThat(outputRes).isEqualTo(expectRes);
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testCreateIncident() throws Exception {
		Incident incident = new Incident(18, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		
		String inputInJSON = this.asJsonString(incident);
		
		when(inciServ.createIncident(Mockito.any(Incident.class))).thenReturn(incident);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/incidents").accept(MediaType.APPLICATION_JSON).content(inputInJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request).andReturn();
		
		String outputInJSON = result.getResponse().getContentAsString();
		
		assertThat(outputInJSON).isEqualTo(inputInJSON);
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testUpdateIncident() throws Exception {
		Incident incident = new Incident(18, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		
		String inputInJSON = this.asJsonString(incident);
		
		when(inciServ.createIncident(Mockito.any(Incident.class))).thenReturn(incident);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/incidents").accept(MediaType.APPLICATION_JSON).content(inputInJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request).andReturn();
		
		String createdJSON = result.getResponse().getContentAsString();
		
		
		incident.setUserId(876);
		incident.setInciPriority("Low");
		incident.setInciStatus("Resolved");
		
		String updatedInputInJSON = this.asJsonString(incident);
		
		when(inciServ.updateIncident(Mockito.any(Incident.class))).thenReturn(incident);
		
		RequestBuilder request2 = MockMvcRequestBuilders.put("/incidents").accept(MediaType.APPLICATION_JSON).content(updatedInputInJSON).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result2 = mvc.perform(request2).andReturn();
		
		String outputInJSON = result2.getResponse().getContentAsString();
		
		assertThat(outputInJSON).isEqualTo(updatedInputInJSON).isNotEqualTo(inputInJSON);
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testGetIncidentsByUser() throws Exception {
		Incident incident = new Incident(18, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		Incident incident2 = new Incident(19, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		Incident incident3 = new Incident(20, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		
		List<Incident> list = new ArrayList<>();
		list.add(incident);
		list.add(incident2);
		list.add(incident3);
		
		when(inciDao.findByUserId(Mockito.anyLong())).thenReturn(list);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/incidents/user/532").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(request).andReturn();
		 
		String expectRes = ControllerTest.asJsonString(list);
		String outputRes = result.getResponse().getContentAsString();
		
		assertThat(outputRes).isEqualTo(expectRes);
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	void testdeleteIncident() throws Exception {
		Incident incident = new Incident(18, "Server DRC down", "abcdefgh", "High", "Hardware Issues", "New", 532, "Neha", "IT");
		
//		Mockito.when(inciServ.deleteIncident(Mockito.anyLong())).thenReturn(incident);
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/incidents/18");
		MvcResult result = mvc.perform(request).andReturn();
		
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
