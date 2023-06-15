package com.usecase4.IncidentManagement.services;

import com.usecase4.IncidentManagement.entity.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentService {

	List<Incident> getIncidents();

	Optional<Incident> getIncident(long incidentId);

	Incident createIncident(Incident incident);

	Incident updateIncident(Long incidentId, Incident updatedIncident);

	void deleteIncident(long incidentId);
}
