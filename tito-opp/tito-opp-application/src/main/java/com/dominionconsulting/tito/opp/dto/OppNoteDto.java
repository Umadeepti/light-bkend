package com.dominionconsulting.tito.opp.dto;

import java.util.Date;

import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityNote;
import com.dominionconsulting.tito.opp.model.Person;

public class OppNoteDto {
	
	private Integer id;
	
	private PersonDto creator;
	
	private String note;
	
	public OppNoteDto() {}
	
	public OppNoteDto(OpportunityNote oppNote) {
		id = oppNote.getId();
		Person person = oppNote.getCreator();
		if (person != null) {
			creator = new PersonDto(person);
		}
		note = oppNote.getNote();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PersonDto getCreator() {
		return creator;
	}

	public void setCreator(PersonDto creator) {
		this.creator = creator;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OpportunityNote toOppNote() {
		OpportunityNote oppNote = new OpportunityNote();
		Opportunity opp= new Opportunity();
		opp.setId(id);
		//oppNote.setId(id);
		oppNote.setCreateDate(new Date());
		oppNote.setTopic("General");
		oppNote.setNote(note);
		oppNote.setOpportunity(opp);
		Person per= new Person();
		per.setId(id);//Should get the Id from who logged in.
		oppNote.setCreator(per);
		return oppNote;
	}
	
	public void toOpportunityNote(OpportunityNote oppNote) {
		oppNote.setId(id);
		if (creator != null) {
			oppNote.setCreator(creator.toPerson());
		} else {
			oppNote.setCreator(null);
		}
		oppNote.setNote(note);
	}
}
