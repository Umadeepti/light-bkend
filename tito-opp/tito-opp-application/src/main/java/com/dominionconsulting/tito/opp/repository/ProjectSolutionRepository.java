package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.ProjectSolution;

@Repository
public interface ProjectSolutionRepository extends CrudRepository<ProjectSolution, Integer> {
	
}
