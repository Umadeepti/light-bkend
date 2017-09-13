package com.dominionconsulting.tito.opp.common.util;

public enum Status {
	
	OPEN("Open"),
	PROTESTED("Protested"),
	WON("Won"),
	LOST_ON_PRICE("Lost on Price"),
	LOST_ON_TECHNICAL("Lost on Technical");

	private String text;
	
	Status(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
}
