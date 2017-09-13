package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityTimeline;

@Repository
public interface OpportunityTimelineRepository extends CrudRepository<OpportunityTimeline, Integer> {
	
	public List<OpportunityTimeline> findByOpportunityId(Integer oppId);
	
}
