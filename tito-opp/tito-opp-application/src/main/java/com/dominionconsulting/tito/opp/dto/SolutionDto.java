package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Solution;

public class SolutionDto {
	
	private Integer id;
	
	private String name;
	
	public SolutionDto() {}
	
	public SolutionDto(Solution solution) {
		id = solution.getId();
		name = solution.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Solution toSolution() {
		Solution solution = new Solution();
		solution.setId(id);
		solution.setName(name);
		return solution;
	}

}
