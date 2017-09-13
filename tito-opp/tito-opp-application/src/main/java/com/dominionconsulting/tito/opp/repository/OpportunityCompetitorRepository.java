package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;

@Repository
public interface OpportunityCompetitorRepository extends CrudRepository<OpportunityCompetitor, Integer> {
	
	public List<OpportunityCompetitor> findByOpportunityId(Integer oppId);
	
}
