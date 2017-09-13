package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;

public class OppPartnerDto {
	
	private Integer id;
	
	private FirmDto firm;
	
	private Boolean exclusive;
	
	private Boolean signedNda;
	
	private Boolean signedTa;
	
	private String workshare;
	
	private String description;
	
	public OppPartnerDto() {}
	
	public OppPartnerDto(OpportunityPartner partner) {
		id = partner.getId();
		Firm firmBean = partner.getFirmBean();
		if (firmBean != null) {
			firm = new FirmDto(firmBean);
		}
		exclusive = partner.getExclusive();
		signedNda = partner.getSignedNda();
		signedTa = partner.getSignedTa();
		workshare = partner.getWorkshare();
		description = partner.getDescription();
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

	public Boolean getExclusive() {
		return exclusive;
	}

	public void setExclusive(Boolean exclusive) {
		this.exclusive = exclusive;
	}

	public Boolean getSignedNda() {
		return signedNda;
	}

	public void setSignedNda(Boolean signedNda) {
		this.signedNda = signedNda;
	}

	public Boolean getSignedTa() {
		return signedTa;
	}

	public void setSignedTa(Boolean signedTa) {
		this.signedTa = signedTa;
	}

	public String getWorkshare() {
		return workshare;
	}

	public void setWorkshare(String workshare) {
		this.workshare = workshare;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void toOpportunityPartner(OpportunityPartner partner) {
		partner.setId(id);
		if (firm != null) {
			partner.setFirmBean(firm.toFirm());
		} else {
			partner.setFirmBean(null);
		}
		partner.setExclusive(exclusive);
		partner.setSignedNda(signedNda);
		partner.setSignedTa(signedTa);
		partner.setWorkshare(workshare);
		partner.setDescription(description);
	}

}
