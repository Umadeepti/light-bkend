package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opportunity_competitors database table.
 * 
 */
@Entity
@Table(name="opportunity_competitors")
@NamedQuery(name="OpportunityCompetitor.findAll", query="SELECT o FROM OpportunityCompetitor o")
public class OpportunityCompetitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="likely_prime")
	private Boolean likelyPrime;

	@Column(length=1000)
	private String note;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	@JoinColumn(name="firm", nullable=false)
	private Firm firmBean;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunity;

	public OpportunityCompetitor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getLikelyPrime() {
		return this.likelyPrime;
	}

	public void setLikelyPrime(Boolean likelyPrime) {
		this.likelyPrime = likelyPrime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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