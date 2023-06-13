package com.example.IncidentManagement.LowesApi.services;

import com.example.IncidentManagement.LowesApi.dao.IncidentDao;
import com.example.IncidentManagement.LowesApi.entity.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {

//	List<Incident> list;

	@Autowired
	private IncidentDao inciDao;

	public IncidentServiceImpl() {

	}

	public IncidentServiceImpl(IncidentDao inciDao) {

		this.inciDao = inciDao;
	}

	@Override
	public List<Incident> getIncidents() {
		
		return inciDao.findAll();
	}


	@Override
	public Optional<Incident> getIncident(long incidentId) {

		return inciDao.findById(incidentId);
	}

	@Override
	public Incident createIncident(Incident inci) {

		inciDao.save(inci);

		return inci;
	}

	@Override
	public Incident updateIncident(Incident inci) {
		
		inciDao.save(inci);
		
		return inci;
	}

	@Override
	public void deleteIncident(long incidentId) {
		
		inciDao.deleteById(incidentId);
		
	}

}
