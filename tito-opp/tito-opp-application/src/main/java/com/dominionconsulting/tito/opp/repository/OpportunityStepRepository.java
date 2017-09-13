package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityNote;
import com.dominionconsulting.tito.opp.model.OpportunityStep;

@Repository
public interface OpportunityStepRepository extends CrudRepository<OpportunityStep, Integer> {
	
	public List<OpportunityStep> findByOpportunityId(Integer oppId);

}
