package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.OpportunityNote;

@Repository
public interface OpportunityNoteRepository extends CrudRepository<OpportunityNote, Integer> {

	public List<OpportunityNote> findByOpportunityId(Integer oppId);
	
}
