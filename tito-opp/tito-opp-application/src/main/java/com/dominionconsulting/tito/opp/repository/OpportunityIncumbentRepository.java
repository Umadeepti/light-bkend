package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityIncumbent;

@Repository
public interface OpportunityIncumbentRepository extends CrudRepository<OpportunityIncumbent, Integer> {

	public List<OpportunityIncumbent> findByOpportunityId(Integer oppId);
	
	public List<OpportunityIncumbent> deleteByFirmBeanAndOpportunity(Firm firmBean, Opportunity opportunity);
	
}
