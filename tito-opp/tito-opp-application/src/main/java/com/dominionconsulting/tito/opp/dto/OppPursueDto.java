package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityStep;

public class OppPursueDto {
	
	private Integer id;

	private Boolean pastPerformance;

	private Boolean alignSolutions;

	private Boolean priceCompetitive;

	private String notes;

	private Boolean pursue;

	public OppPursueDto() {}

	public OppPursueDto(Opportunity opp) {
		id = opp.getId();
		pastPerformance=opp.getSupportingPastPerformance();
		priceCompetitive=opp.getPriceToWin();
		alignSolutions=opp.getTeamAssembled();
		List<OpportunityStep> oppStepList=opp.getOpportunitySteps();
		OpportunityStep oppSteps;
		if(oppStepList!=null){
			oppSteps= oppStepList.get(oppStepList.size()-1);
			notes=oppSteps.getNote();
			pursue=Boolean.parseBoolean(oppSteps.getDecision());
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPastPerformance() {
		return pastPerformance;
	}

	public void setPastPerformance(Boolean pastPerformance) {
		this.pastPerformance = pastPerformance;
	}

	public Boolean getAlignSolutions() {
		return alignSolutions;
	}

	public void setAlignSolutions(Boolean alignSolutions) {
		this.alignSolutions = alignSolutions;
	}

	public Boolean getPriceCompetitive() {
		return priceCompetitive;
	}

	public void setPriceCompetitive(Boolean priceCompetitive) {
		this.priceCompetitive = priceCompetitive;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getPursue() {
		return pursue;
	}

	public void setPursue(Boolean pursue) {
		this.pursue = pursue;
	}
	public Opportunity toPursue(Opportunity opp) {
		
		OppStepDto oppStep;		
		opp.setId(id);
		opp.setSupportingPastPerformance(pastPerformance);
		opp.setPriceToWin(priceCompetitive);
		opp.setTeamAssembled(alignSolutions);
	
		
		 List<OpportunityStep> oppStepList=opp.getOpportunitySteps();
		 if(oppStepList==null){
			 oppStepList= new ArrayList<OpportunityStep>();

		 }
		 oppStep= new OppStepDto();
		 oppStep.setId(id);
		 oppStep.setName("Bid Decision");
		 oppStep.setNote(notes);
		 oppStep.setDecision(pursue.toString());
		 oppStepList.add(oppStep.toOppStep());
		 if (oppStep != null) opp.setOpportunitySteps(oppStepList);

		return opp;
	}

}
