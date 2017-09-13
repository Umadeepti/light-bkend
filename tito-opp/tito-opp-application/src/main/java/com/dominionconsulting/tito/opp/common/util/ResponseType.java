package com.dominionconsulting.tito.opp.common.util;

public enum ResponseType {
	RFI("RFI"),
	RFP("RFP");
	
	private String text;
	
	ResponseType(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
