package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Firm;

@Repository
public interface FirmRepository extends CrudRepository<Firm, Integer> {
	
}
