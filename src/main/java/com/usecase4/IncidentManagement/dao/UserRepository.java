package com.usecase4.IncidentManagement.dao;

import com.usecase4.IncidentManagement.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
