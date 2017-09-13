package com.dominionconsulting.tito.opp.dto;

import java.util.Date;

import com.dominionconsulting.tito.opp.common.util.EventStatus;
import com.dominionconsulting.tito.opp.model.OpportunityEvent;

public class OppEventDto {
	
	private Integer id;
	
	private String activity;
	
	private Date date;
	
	private String note;
	
	private boolean status;
	
	public OppEventDto() {}
	
	public OppEventDto(OpportunityEvent event) {
		id = event.getId();
		activity = event.getEvent();
		if(event.getDate()!=null){
		date = event.getDate();}
		if(event.getNote()!=null){
		note = event.getNote();}
		if(event.getStatus()!=null){
		//status = (event.getStatus().equals(EventStatus.COMPLETE)) ? true : false;
		status=(event.getStatus().equalsIgnoreCase("true")) ? true : false;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void toOpportunityEvent(OpportunityEvent event) {
		event.setId(id);
		event.setEvent(activity);
		event.setDate(date);
		event.setNote(note);
		if(this.isStatus()){
			event.setStatus("true");
		}
		else{
			event.setStatus("false");
		}
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
