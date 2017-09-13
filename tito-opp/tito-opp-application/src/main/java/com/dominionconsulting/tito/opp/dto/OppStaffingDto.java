package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Opportunity;

public class OppStaffingDto {

	private String placeholder;

	public OppStaffingDto() {}

	public OppStaffingDto(Opportunity opp) {}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

}
