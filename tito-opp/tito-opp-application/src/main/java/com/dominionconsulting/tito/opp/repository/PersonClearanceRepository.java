package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.PersonClearance;

@Repository
public interface PersonClearanceRepository extends CrudRepository<PersonClearance, Integer> {
	
}
