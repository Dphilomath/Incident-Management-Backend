package com.usecase4.IncidentManagement.controller;

import com.usecase4.IncidentManagement.dao.IncidentDao;
import com.usecase4.IncidentManagement.entity.Incident;
import com.usecase4.IncidentManagement.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class IncidentController {

	@Autowired
	private IncidentService incidentService;

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
		return this.incidentService.getIncidents();
	}
	
	@GetMapping("/incidents/{incidentId}")
	public Optional<Incident> getIncident(@PathVariable String incidentId)
	{
		return this.incidentService.getIncident(Long.parseLong(incidentId));
	}

	@PostMapping("/incidents")
	public Incident createIncident(@RequestBody Incident inci) {

		return this.incidentService.createIncident(inci);
	}

	@PutMapping("/incidents")
	public Incident updateIncident(@RequestBody Incident inci) {
		return this.incidentService.updateIncident(inci);
	}

//	@GetMapping("/incidents/user/{userId}")
//	public ResponseEntity<List<Incident>> getIncidentsByUser(@PathVariable String userId){
//
//		return new ResponseEntity<List<Incident>>(inciDao.findByUserId(Long.parseLong(userId)), HttpStatus.OK);
//	}

	@DeleteMapping("/incidents/{incidentId}")
	public ResponseEntity<HttpStatus> deleteIncident(@PathVariable String incidentId) {
		try {
			this.incidentService.deleteIncident(Long.parseLong(incidentId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
} 
