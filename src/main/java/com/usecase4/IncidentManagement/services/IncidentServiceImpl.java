package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.dao.IncidentRepository;
import com.usecase4.IncidentManagement.entity.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {

//	List<Incident> list;

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

		incidentRepository.save(incident);

		return incident;
	}

	@Override
	public Incident updateIncident(Incident incident) {

		incidentRepository.save(incident);

		return incident;
	}

	@Override
	public void deleteIncident(long incidentId) {

		incidentRepository.deleteById(incidentId);
		
	}

}
