package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityEvent;

@Repository
public interface OpportunityEventRepository extends CrudRepository<OpportunityEvent, Integer> {
	
	public List<OpportunityEvent> findByOpportunityId(Integer oppId);
	
}
