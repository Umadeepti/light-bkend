package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityRole;

@Repository
public interface OpportunityRoleRepository extends CrudRepository<OpportunityRole, Integer> {
	
}
