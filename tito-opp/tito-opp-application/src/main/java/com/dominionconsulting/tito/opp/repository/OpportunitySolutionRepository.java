package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunitySolution;

@Repository
public interface OpportunitySolutionRepository extends CrudRepository<OpportunitySolution, Integer> {
	
	public List<OpportunitySolution> findByOpportunityId(@Param("oppId")Integer oppId);
	
	public List<OpportunitySolution> findByOpportunity(Opportunity opp);
	
}
