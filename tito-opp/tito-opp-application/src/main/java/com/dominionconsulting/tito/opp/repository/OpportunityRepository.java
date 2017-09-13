package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Opportunity;

@Repository
public interface OpportunityRepository extends CrudRepository<Opportunity, Integer> {
	
}
