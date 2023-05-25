package com.example.IncidentManagement.LowesApi.services;

import java.util.List;
import java.util.Optional;

import com.example.IncidentManagement.LowesApi.entity.Incident;

public interface InciService {
	
	public List<Incident> getIncidents();

	public Optional<Incident> getincident(long incidentId);

	public Incident createIncident(Incident inci);

	public Incident updateIncident(Incident inci);

	public void deleteIncident(long incidentId);
}
