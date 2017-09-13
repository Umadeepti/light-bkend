package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;

public class OppCaptureActivitiesDto {
	
	private List<OppEventDto> activities;
	
	public OppCaptureActivitiesDto() {}
	
	public OppCaptureActivitiesDto(Opportunity opp) {
		activities = new ArrayList<OppEventDto>();
		List<OpportunityEvent> events = opp.getOpportunityEvents();
		if (events != null) {
			for (OpportunityEvent event : events) {
				activities.add(new OppEventDto(event));
			}
		}
	}

	public List<OppEventDto> getActivities() {
		return activities;
	}

	public void setActivities(List<OppEventDto> activities) {
		this.activities = activities;
	}
	
}
