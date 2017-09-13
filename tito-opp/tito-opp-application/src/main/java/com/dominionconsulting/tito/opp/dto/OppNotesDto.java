package com.dominionconsulting.tito.opp.dto;

public class OppNotesDto {
	
	private String text;
	
	public OppNotesDto() {}
	
	public OppNotesDto(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
