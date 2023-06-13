package com.example.IncidentManagement.LowesApi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.IncidentManagement.LowesApi.dao.IncidentDao;
import com.example.IncidentManagement.LowesApi.entity.Incident;
import com.example.IncidentManagement.LowesApi.services.InciService;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	@Autowired
	private InciService inciService;
	
	@Autowired
	private IncidentDao inciDao;
	
	@GetMapping("/home")
	public String home() {
		
		return "Welcome to course";
	}
	
	//Get all Incidents
	@GetMapping("/incidents")
	public List<Incident> getIncidents()
	{
		return this.inciService.getIncidents();
	}
	
	@GetMapping("/incidents/{incidentId}")
	public Optional<Incident> getIncident(@PathVariable String incidentId)
	{
		return this.inciService.getincident(Long.parseLong(incidentId));
	}
	
	@PostMapping("/incidents")
	public Incident createIncident(@RequestBody Incident inci) {
		
		return this.inciService.createIncident(inci);
	}
	
	@PutMapping("/incidents")
	public Incident updateIncident(@RequestBody Incident inci) {
		return this.inciService.updateIncident(inci);
	}
	
	@GetMapping("/incidents/user/{userId}")
	public ResponseEntity<List<Incident>> getIncidentsByUser(@PathVariable String userId){
		
		return new ResponseEntity<List<Incident>>(inciDao.findByUserId(Long.parseLong(userId)), HttpStatus.OK);
	}
	
	@DeleteMapping("/incidents/{incidentId}")
	public ResponseEntity<HttpStatus> deleteIncident(@PathVariable String incidentId)
	{
		try {
			this.inciService.deleteIncident(Long.parseLong(incidentId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
} 
