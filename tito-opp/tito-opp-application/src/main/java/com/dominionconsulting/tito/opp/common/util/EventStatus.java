package com.dominionconsulting.tito.opp.common.util;

public enum EventStatus {
	PENDING("Pending"), COMPLETE("Complete"), TRUE("true");
	
	private String value;
	
	private EventStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
