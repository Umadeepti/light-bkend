package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.OpportunityIncumbent;

public class OppIncumbentDto {
	
	private Integer id;
	
	private FirmDto firm;
	
	public OppIncumbentDto() {}
	
	public OppIncumbentDto(OpportunityIncumbent incumbent) {
		id = incumbent.getId();
		Firm firmBean = incumbent.getFirmBean();
		if (firmBean != null) {
			firm = new FirmDto(firmBean);
		}
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
	
	public void toOpportunityIncumbent(OpportunityIncumbent incumbent) {
		incumbent.setId(id);
		if (firm != null) {
			incumbent.setFirmBean(firm.toFirm());
		} else {
			incumbent.setFirmBean(null);
		}
	}

}
