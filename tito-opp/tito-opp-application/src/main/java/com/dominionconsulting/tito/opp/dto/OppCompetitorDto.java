package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;

public class OppCompetitorDto {
	private Integer id;
	
	private FirmDto firm;
	
	private String note;

	public OppCompetitorDto() {}

	public OppCompetitorDto(OpportunityCompetitor competitor) {
		id = competitor.getId();
		Firm firmBean = competitor.getFirmBean();
		if (firmBean != null) {
			firm = new FirmDto(firmBean);
		}
		note = competitor.getNote();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FirmDto getFirm() {
		return firm;
	}

	public void setFirm(FirmDto firm) {
		this.firm = firm;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void toOpportunityCompetitor(OpportunityCompetitor competitor) {
		competitor.setId(id);
		if (firm != null) {
			competitor.setFirmBean(firm.toFirm());
		} else {
			competitor.setFirmBean(null);
		}
		competitor.setNote(note);
	}

}
