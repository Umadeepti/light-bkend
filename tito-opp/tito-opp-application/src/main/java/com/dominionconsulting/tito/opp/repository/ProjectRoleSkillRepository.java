package com.dominionconsulting.tito.opp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dominionconsulting.tito.opp.model.ProjectRoleSkill;

@Repository
public interface ProjectRoleSkillRepository extends CrudRepository<ProjectRoleSkill, Integer> {
	
}
