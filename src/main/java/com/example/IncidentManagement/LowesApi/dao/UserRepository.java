package com.example.IncidentManagement.LowesApi.dao;

import com.example.IncidentManagement.LowesApi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
