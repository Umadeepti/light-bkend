package com.dominionconsulting.tito.opp.common.util;

public enum Stage {
	
	QUALIFYING("Qualifying"),
	QUALIFIED("Qualified"),
	PROPOSAL_REVIEW("Proposal/Review"),
	PROTESTED_PENDING("Protested/Pending");
	
	private String text;
	
	Stage(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
