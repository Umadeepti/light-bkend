package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
}
