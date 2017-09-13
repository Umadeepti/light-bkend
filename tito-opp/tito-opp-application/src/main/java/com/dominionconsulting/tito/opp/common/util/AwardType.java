package com.dominionconsulting.tito.opp.common.util;

public enum AwardType {
	
	FIRM_FIXED_PRICE("Firm Fixed Price"),
	TIME_AND_MATERIALS("Time and Materials");
	
	private String text;
	
	AwardType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
