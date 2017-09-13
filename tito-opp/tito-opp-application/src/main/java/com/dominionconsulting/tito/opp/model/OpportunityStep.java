package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the opportunity_steps database table.
 * 
 */
@Entity
@Table(name="opportunity_steps")
@NamedQuery(name="OpportunityStep.findAll", query="SELECT o FROM OpportunityStep o")
public class OpportunityStep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=30)
	private String decision;

	@Column(nullable=false, length=30)
	private String name;

	@Column(length=1000)
	private String note;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="review_date")
	private Date reviewDate;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunity;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="approver")
	private Person approver;

	public OpportunityStep() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDecision() {
		return this.decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getReviewDate() {
		return this.reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Opportunity getOpportunity() {
		return this.opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	public Person getApprover() {
		return this.approver;
	}

	public void setApprover(Person approver) {
		this.approver = approver;
	}

}