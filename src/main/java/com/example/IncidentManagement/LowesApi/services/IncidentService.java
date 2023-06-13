package com.example.IncidentManagement.LowesApi.services;

import com.example.IncidentManagement.LowesApi.entity.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentService {

	List<Incident> getIncidents();

	Optional<Incident> getIncident(long incidentId);

	Incident createIncident(Incident incident);

	Incident updateIncident(Incident incident);

	void deleteIncident(long incidentId);
}
