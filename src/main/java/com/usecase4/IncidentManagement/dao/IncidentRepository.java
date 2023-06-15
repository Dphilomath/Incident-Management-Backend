package com.usecase4.IncidentManagement.dao;

import com.usecase4.IncidentManagement.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

//	List<Incident> findByUserId(Integer userId);
}
