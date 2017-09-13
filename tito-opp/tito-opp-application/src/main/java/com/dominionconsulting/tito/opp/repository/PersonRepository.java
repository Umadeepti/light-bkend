package com.dominionconsulting.tito.opp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
	
}
