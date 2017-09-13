package com.dominionconsulting.tito.opp.dto;

import java.util.Date;

import com.dominionconsulting.tito.opp.model.OpportunityTimeline;

public class OppTimelineDto {
	
	private Integer id;
	
	private Date date;
	
	public OppTimelineDto() {}
	
	public OppTimelineDto(OpportunityTimeline timeline) {
		id = timeline.getId();
		date = timeline.getDate();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void toOpportunityTimeline(OpportunityTimeline timeline) {
		timeline.setId(id);
		timeline.setDate(date);
	}

}
