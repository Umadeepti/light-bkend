package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Opportunity;

public class OppNewDto {
	
	private Integer id;
	
	private OppSummaryDto summary;
	
	private OppDetailsDto details;
	
	public OppNewDto() {}

	public OppNewDto(Opportunity opp) {
		id = opp.getId();
		summary = new OppSummaryDto(opp);
		details = new OppDetailsDto(opp);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OppSummaryDto getSummary() {
		return summary;
	}

	public void setSummary(OppSummaryDto summary) {
		this.summary = summary;
	}

	public OppDetailsDto getDetails() {
		return details;
	}

	public void setDetails(OppDetailsDto details) {
		this.details = details;
	}
	
	public Opportunity toOpportunity() {
		Opportunity opp = new Opportunity();
		opp.setId(id);
		if (summary != null) summary.toOpportunity(opp);
		if (details != null) details.toOpportunity(opp);
		return opp;
	}

}
