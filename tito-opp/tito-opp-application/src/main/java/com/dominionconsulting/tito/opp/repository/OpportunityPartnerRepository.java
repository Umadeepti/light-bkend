package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityPartner;

@Repository
public interface OpportunityPartnerRepository extends CrudRepository<OpportunityPartner, Integer> {
	
	public List<OpportunityPartner> findByOpportunityId(Integer oppId);
	
}
