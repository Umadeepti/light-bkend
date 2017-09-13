package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Firm;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.OpportunityCompetitor;
import com.dominionconsulting.tito.opp.model.OpportunityIncumbent;
import com.dominionconsulting.tito.opp.model.OpportunityPartner;

public class OppLandscapeDto {
	
	private List<FirmDto> firms;
	
	private FirmDto primingPartner;
	
	private List<OppPartnerDto> partners;
	
	private List<OppCompetitorDto> competitors;
	
	private List<OppIncumbentDto> existingIncumbents;
	
	private List<OppIncumbentDto> addedIncumbents;
	
	private List<OppIncumbentDto> removedIncumbents;
	
	public OppLandscapeDto() {}

	public OppLandscapeDto(Opportunity opp) {
		Firm firm = opp.getPrimingPartner();
		if (firm != null) {
			primingPartner = new FirmDto(firm);
		}
		
		List<OpportunityPartner> oppPartners = opp.getOpportunityPartners();
		partners = new ArrayList<OppPartnerDto>();
		if (oppPartners != null) {
			for (OpportunityPartner partner : oppPartners) {
				partners.add(new OppPartnerDto(partner));
			}
		}
		
		List<OpportunityCompetitor> oppCompetitors = opp.getOpportunityCompetitors();
		competitors = new ArrayList<OppCompetitorDto>();
		if (oppCompetitors != null) {
			for (OpportunityCompetitor competitor : oppCompetitors) {
				competitors.add(new OppCompetitorDto(competitor));
			}
		}
		
		List<OpportunityIncumbent> oppIncumbents = opp.getOpportunityIncumbents();
		existingIncumbents = new ArrayList<OppIncumbentDto>();
		if (oppIncumbents != null) {
			for (OpportunityIncumbent incumbent : oppIncumbents) {
				existingIncumbents.add(new OppIncumbentDto(incumbent));
			}
		}
	}

	public List<FirmDto> getFirms() {
		return firms;
	}

	public void setFirms(List<FirmDto> firms) {
		this.firms = firms;
	}

	public FirmDto getPrimingPartner() {
		return primingPartner;
	}

	public void setPrimingPartner(FirmDto primingPartner) {
		this.primingPartner = primingPartner;
	}

	public List<OppPartnerDto> getPartners() {
		return partners;
	}

	public void setPartners(List<OppPartnerDto> partners) {
		this.partners = partners;
	}

	public List<OppCompetitorDto> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<OppCompetitorDto> competitors) {
		this.competitors = competitors;
	}

	public List<OppIncumbentDto> getExistingIncumbents() {
		return existingIncumbents;
	}

	public void setExistingIncumbents(List<OppIncumbentDto> existingIncumbents) {
		this.existingIncumbents = existingIncumbents;
	}

	public List<OppIncumbentDto> getAddedIncumbents() {
		return addedIncumbents;
	}

	public void setAddedIncumbents(List<OppIncumbentDto> addedIncumbents) {
		this.addedIncumbents = addedIncumbents;
	}

	public List<OppIncumbentDto> getRemovedIncumbents() {
		return removedIncumbents;
	}

	public void setRemovedIncumbents(List<OppIncumbentDto> removedIncumbents) {
		this.removedIncumbents = removedIncumbents;
	}

	public void toOpportunity(Opportunity opp) {
		if (primingPartner != null) {
			opp.setPrimingPartner(primingPartner.toFirm());
		} else {
			opp.setPrimingPartner(null);
		}
	}

}
