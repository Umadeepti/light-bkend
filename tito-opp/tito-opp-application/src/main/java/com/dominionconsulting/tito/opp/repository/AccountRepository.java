package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
}
