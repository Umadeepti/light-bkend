package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.OpportunitySolution;

public class OppSolutionDto {
	
	private Integer id;
	
	private SolutionDto solution;
	
	public OppSolutionDto() {}
	
	public OppSolutionDto(OpportunitySolution oppSolution) {
		id = oppSolution.getId();
		solution = new SolutionDto(oppSolution.getSolutionBean());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SolutionDto getSolution() {
		return solution;
	}

	public void setSolution(SolutionDto solution) {
		this.solution = solution;
	}

	public void toOpportunitySolution(OpportunitySolution oppSolution) {
		oppSolution.setId(id);
		if (solution != null) {
			oppSolution.setSolutionBean(solution.toSolution());
		} else {
			oppSolution.setSolutionBean(null);
		}
	}
}
