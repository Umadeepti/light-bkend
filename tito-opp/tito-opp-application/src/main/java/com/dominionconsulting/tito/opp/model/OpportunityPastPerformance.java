package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opportunity_past_performances database table.
 * 
 */
@Entity
@Table(name="opportunity_past_performances")
@NamedQuery(name="OpportunityPastPerformance.findAll", query="SELECT o FROM OpportunityPastPerformance o")
public class OpportunityPastPerformance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String note;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunityBean;

	//bi-directional many-to-one association to PastPerformance
	@ManyToOne
	@JoinColumn(name="past_performance", nullable=false)
	private PastPerformance pastPerformanceBean;

	public OpportunityPastPerformance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Opportunity getOpportunityBean() {
		return this.opportunityBean;
	}

	public void setOpportunityBean(Opportunity opportunityBean) {
		this.opportunityBean = opportunityBean;
	}

	public PastPerformance getPastPerformanceBean() {
		return this.pastPerformanceBean;
	}

	public void setPastPerformanceBean(PastPerformance pastPerformanceBean) {
		this.pastPerformanceBean = pastPerformanceBean;
	}

}