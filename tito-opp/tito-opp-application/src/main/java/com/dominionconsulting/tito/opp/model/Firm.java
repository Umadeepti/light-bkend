package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the firms database table.
 * 
 */
@Entity
@Table(name="firms")
@NamedQuery(name="Firm.findAll", query="SELECT f FROM Firm f")
public class Firm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=60)
	private String name;

	//bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy="primingPartner")
	private List<Opportunity> opportunities;

	//bi-directional many-to-one association to OpportunityCompetitor
	@OneToMany(mappedBy="firmBean")
	private List<OpportunityCompetitor> opportunityCompetitors;

	//bi-directional many-to-one association to OpportunityIncumbent
	@OneToMany(mappedBy="firmBean")
	private List<OpportunityIncumbent> opportunityIncumbents;

	//bi-directional many-to-one association to OpportunityPartner
	@OneToMany(mappedBy="firmBean")
	private List<OpportunityPartner> opportunityPartners;

	//bi-directional many-to-one association to PartnerSkill
	@OneToMany(mappedBy="firmBean")
	private List<PartnerSkill> partnerSkills;

	public Firm() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Opportunity> getOpportunities() {
		return this.opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public Opportunity addOpportunity(Opportunity opportunity) {
		getOpportunities().add(opportunity);
		opportunity.setPrimingPartner(this);

		return opportunity;
	}

	public Opportunity removeOpportunity(Opportunity opportunity) {
		getOpportunities().remove(opportunity);
		opportunity.setPrimingPartner(null);

		return opportunity;
	}

	public List<OpportunityCompetitor> getOpportunityCompetitors() {
		return this.opportunityCompetitors;
	}

	public void setOpportunityCompetitors(List<OpportunityCompetitor> opportunityCompetitors) {
		this.opportunityCompetitors = opportunityCompetitors;
	}

	public OpportunityCompetitor addOpportunityCompetitor(OpportunityCompetitor opportunityCompetitor) {
		getOpportunityCompetitors().add(opportunityCompetitor);
		opportunityCompetitor.setFirmBean(this);

		return opportunityCompetitor;
	}

	public OpportunityCompetitor removeOpportunityCompetitor(OpportunityCompetitor opportunityCompetitor) {
		getOpportunityCompetitors().remove(opportunityCompetitor);
		opportunityCompetitor.setFirmBean(null);

		return opportunityCompetitor;
	}

	public List<OpportunityIncumbent> getOpportunityIncumbents() {
		return this.opportunityIncumbents;
	}

	public void setOpportunityIncumbents(List<OpportunityIncumbent> opportunityIncumbents) {
		this.opportunityIncumbents = opportunityIncumbents;
	}

	public OpportunityIncumbent addOpportunityIncumbent(OpportunityIncumbent opportunityIncumbent) {
		getOpportunityIncumbents().add(opportunityIncumbent);
		opportunityIncumbent.setFirmBean(this);

		return opportunityIncumbent;
	}

	public OpportunityIncumbent removeOpportunityIncumbent(OpportunityIncumbent opportunityIncumbent) {
		getOpportunityIncumbents().remove(opportunityIncumbent);
		opportunityIncumbent.setFirmBean(null);

		return opportunityIncumbent;
	}

	public List<OpportunityPartner> getOpportunityPartners() {
		return this.opportunityPartners;
	}

	public void setOpportunityPartners(List<OpportunityPartner> opportunityPartners) {
		this.opportunityPartners = opportunityPartners;
	}

	public OpportunityPartner addOpportunityPartner(OpportunityPartner opportunityPartner) {
		getOpportunityPartners().add(opportunityPartner);
		opportunityPartner.setFirmBean(this);

		return opportunityPartner;
	}

	public OpportunityPartner removeOpportunityPartner(OpportunityPartner opportunityPartner) {
		getOpportunityPartners().remove(opportunityPartner);
		opportunityPartner.setFirmBean(null);

		return opportunityPartner;
	}

	public List<PartnerSkill> getPartnerSkills() {
		return this.partnerSkills;
	}

	public void setPartnerSkills(List<PartnerSkill> partnerSkills) {
		this.partnerSkills = partnerSkills;
	}

	public PartnerSkill addPartnerSkill(PartnerSkill partnerSkill) {
		getPartnerSkills().add(partnerSkill);
		partnerSkill.setFirmBean(this);

		return partnerSkill;
	}

	public PartnerSkill removePartnerSkill(PartnerSkill partnerSkill) {
		getPartnerSkills().remove(partnerSkill);
		partnerSkill.setFirmBean(null);

		return partnerSkill;
	}

}