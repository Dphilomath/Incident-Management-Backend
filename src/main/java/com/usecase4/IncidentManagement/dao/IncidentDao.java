package com.usecase4.IncidentManagement.dao;

import com.usecase4.IncidentManagement.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentDao extends JpaRepository<Incident, Long> {
	
	List<Incident> findByUserId(long userId);
}
