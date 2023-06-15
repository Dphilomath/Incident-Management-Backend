package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.dao.IncidentRepository;
import com.usecase4.IncidentManagement.entity.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	public IncidentServiceImpl() {

	}

	public IncidentServiceImpl(IncidentRepository incidentRepository) {

		this.incidentRepository = incidentRepository;
	}

	@Override
	public List<Incident> getIncidents() {
		
		return incidentRepository.findAll();
	}


	@Override
	public Optional<Incident> getIncident(long incidentId) {

		return incidentRepository.findById(incidentId);
	}

	@Override
	public Incident createIncident(Incident incident) {

		Incident saved = incidentRepository.save(incident);

		return saved;
	}

//	@Override
//	public Incident updateIncident(Incident incident) {
//
//		Incident updated = incidentRepository.save(incident);
//
//		return updated;
//	}

	@Override
	public Incident updateIncident(Long incidentId, Incident updatedIncident) {
		Optional<Incident> optionalIncident = incidentRepository.findById(incidentId);

		if (optionalIncident.isPresent()) {
			Incident existingIncident = optionalIncident.get();
			if (updatedIncident.getInciName() != null)
				existingIncident.setInciName(updatedIncident.getInciName());
			if (updatedIncident.getDescription() != null)
				existingIncident.setDescription(updatedIncident.getDescription());
			if (updatedIncident.getInciStatus() != null)
				existingIncident.setInciStatus(updatedIncident.getInciStatus());
			if (updatedIncident.getUser() != null)
				existingIncident.setUser(updatedIncident.getUser());
			if (updatedIncident.getInciPriority() != null)
				existingIncident.setInciPriority(updatedIncident.getInciPriority());
			if (updatedIncident.getInciCategory() != null)
				existingIncident.setInciCategory(updatedIncident.getInciCategory());
			return incidentRepository.save(existingIncident);
		} else {
			throw new NoSuchElementException("Incident with ID: " + incidentId + " not found");
		}
	}

	@Override
	public void deleteIncident(long incidentId) {

		incidentRepository.deleteById(incidentId);

	}

}
