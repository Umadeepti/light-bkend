package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opportunity_partners database table.
 * 
 */
@Entity
@Table(name="opportunity_partners")
@NamedQuery(name="OpportunityPartner.findAll", query="SELECT o FROM OpportunityPartner o")
public class OpportunityPartner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;
	@Column(length=1000)
	private String description;

	private Boolean exclusive;

	@Column(name="signed_nda")
	private Boolean signedNda;

	@Column(name="signed_ta")
	private Boolean signedTa;

	@Column(length=30)
	private String workshare;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	@JoinColumn(name="firm", nullable=false)
	private Firm firmBean;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunity;

	public OpportunityPartner() {
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

	public Boolean getExclusive() {
		return this.exclusive;
	}

	public void setExclusive(Boolean exclusive) {
		this.exclusive = exclusive;
	}

	public Boolean getSignedNda() {
		return this.signedNda;
	}

	public void setSignedNda(Boolean signedNda) {
		this.signedNda = signedNda;
	}

	public Boolean getSignedTa() {
		return this.signedTa;
	}

	public void setSignedTa(Boolean signedTa) {
		this.signedTa = signedTa;
	}

	public String getWorkshare() {
		return this.workshare;
	}

	public void setWorkshare(String workshare) {
		this.workshare = workshare;
	}
	public Firm getFirmBean() {
		return this.firmBean;
	}

	public void setFirmBean(Firm firmBean) {
		this.firmBean = firmBean;
	}

	public Opportunity getOpportunity() {
		return this.opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

}