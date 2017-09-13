package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opportunity_solutions database table.
 * 
 */
@Entity
@Table(name="opportunity_solutions")
@NamedQuery(name="OpportunitySolution.findAll", query="SELECT o FROM OpportunitySolution o")
public class OpportunitySolution implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunity;

	//bi-directional many-to-one association to Solution
	@ManyToOne
	@JoinColumn(name="solution", nullable=false)
	private Solution solutionBean;

	public OpportunitySolution() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Opportunity getOpportunity() {
		return this.opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	public Solution getSolutionBean() {
		return this.solutionBean;
	}

	public void setSolutionBean(Solution solutionBean) {
		this.solutionBean = solutionBean;
	}

}