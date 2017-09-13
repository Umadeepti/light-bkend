package com.dominionconsulting.tito.opp.common.util;

public enum Milestone {
	
	PROPOSAL_DUE("Proposal Due Date"),
	EST_AWARD("Award Date");
	
	private String text;
	
	Milestone(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
