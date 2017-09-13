package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opportunity_incumbents database table.
 * 
 */
@Entity
@Table(name="opportunity_incumbents")
@NamedQuery(name="OpportunityIncumbent.findAll", query="SELECT o FROM OpportunityIncumbent o")
public class OpportunityIncumbent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	@JoinColumn(name="firm", nullable=false)
	private Firm firmBean;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunity;

	public OpportunityIncumbent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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