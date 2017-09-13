package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Opportunity;

public class OppDetailDto {
	
	private Integer id;
	
	private OppSummaryDto summary;
	
	private OppDetailsDto details;
	
	private OppLandscapeDto landscape;
	
	private OppCaptureActivitiesDto captureActivities;
	
	private OppStaffingDto staffing;
	
	public OppDetailDto() {}

	public OppDetailDto(Opportunity opp) {
		id = opp.getId();
		summary = new OppSummaryDto(opp);
		details = new OppDetailsDto(opp);
		landscape = new OppLandscapeDto(opp);
		captureActivities = new OppCaptureActivitiesDto(opp);
		staffing = new OppStaffingDto(opp);
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

	public OppLandscapeDto getLandscape() {
		return landscape;
	}

	public void setLandscape(OppLandscapeDto landscape) {
		this.landscape = landscape;
	}

	public OppCaptureActivitiesDto getCaptureActivities() {
		return captureActivities;
	}

	public void setCaptureActivities(OppCaptureActivitiesDto captureActivities) {
		this.captureActivities = captureActivities;
	}

	public OppStaffingDto getStaffing() {
		return staffing;
	}

	public void setStaffing(OppStaffingDto staffing) {
		this.staffing = staffing;
	}

	public void toOpportunity(Opportunity opp) {
		opp.setId(id);
		summary.toOpportunity(opp);
		details.toOpportunity(opp);
		landscape.toOpportunity(opp);
	}
}
