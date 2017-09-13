package com.dominionconsulting.tito.opp.dto;

import java.util.Date;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityStep;

public class OppStepDto {
	
	private Integer id;
	
	private String decision;

	private String name;

	private String note;

	private Date reviewDate;
	
	public OppStepDto() {}
	
	public OppStepDto(OpportunityStep oppStep) {
		id = oppStep.getId();
		name= oppStep.getName();
		reviewDate=oppStep.getReviewDate();
		note = oppStep.getNote();
		decision=oppStep.getDecision();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OpportunityStep toOppStep() {
		OpportunityStep oppStep = new OpportunityStep();
		Opportunity opp= new Opportunity();
		//oppStep.setId(id);
		opp.setId(id);
		oppStep.setReviewDate(new Date());
		if(this.name==null){
			oppStep.setName("General");
		}
		else{
			oppStep.setName(name);
		}
		oppStep.setNote(note);
		oppStep.setDecision(decision);
		oppStep.setOpportunity(opp);
		
		return oppStep;
	}
}
