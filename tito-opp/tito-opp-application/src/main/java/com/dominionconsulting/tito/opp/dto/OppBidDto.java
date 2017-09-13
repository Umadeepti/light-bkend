package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityStep;

public class OppBidDto {
	private Integer id;

	private Boolean teamAssembled;

	private Boolean crediblePrime;
	
	private Integer bpBudgetAmount;

	private Boolean priceCompetitive;

	private String notes;

	private Boolean bid;

	public OppBidDto() {}

	public OppBidDto(Opportunity opp) {
		id = opp.getId();
		teamAssembled=opp.getTeamAssembled();

		priceCompetitive=opp.getPriceToWin();
		crediblePrime=opp.getCrediblePrime();
		bpBudgetAmount=opp.getBpBudgetAmount();
		List<OpportunityStep> oppStepList=opp.getOpportunitySteps();
		OpportunityStep oppSteps = null;
		if(oppStepList!=null){
			for(OpportunityStep oppStep:oppStepList){
				if(oppStep.getName()!=null && oppStep.getName().equalsIgnoreCase("Bid Decision")){
					oppSteps=oppStep;
				}
			}
			if(oppSteps!=null){
				notes=oppSteps.getNote();
				bid=Boolean.parseBoolean(oppSteps.getDecision());
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Boolean getTeamAssembled() {
		return teamAssembled;
	}

	public void setTeamAssembled(Boolean teamAssembled) {
		this.teamAssembled = teamAssembled;
	}

	public Boolean getCrediblePrime() {
		return crediblePrime;
	}

	public void setCrediblePrime(Boolean crediblePrime) {
		this.crediblePrime = crediblePrime;
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

	public Boolean getBid() {
		return bid;
	}

	public void setBid(Boolean bid) {
		this.bid = bid;
	}

	public Integer getBpBudgetAmount() {
		return bpBudgetAmount;
	}

	public void setBpBudgetAmount(Integer bpBudgetAmount) {
		this.bpBudgetAmount = bpBudgetAmount;
	}

	public Opportunity toBid(Opportunity opp) {

		OppStepDto oppStep;		
		opp.setId(id);
		opp.setCrediblePrime(crediblePrime);
		opp.setPriceToWin(priceCompetitive);
		opp.setTeamAssembled(teamAssembled);
		opp.setBpBudgetAmount(bpBudgetAmount);
		List<OpportunityStep> oppStepList=opp.getOpportunitySteps();
		if(oppStepList==null){
			oppStepList= new ArrayList<OpportunityStep>();

		}
		oppStep= new OppStepDto();
		oppStep.setId(id);
		oppStep.setName("Bid Decision");
		oppStep.setNote(notes);
		oppStep.setDecision(bid.toString());
		oppStepList.add(oppStep.toOppStep());
		if (oppStep != null) opp.setOpportunitySteps(oppStepList);

		return opp;
	}
}
