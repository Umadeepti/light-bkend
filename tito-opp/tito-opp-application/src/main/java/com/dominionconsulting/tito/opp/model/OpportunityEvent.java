package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the opportunity_events database table.
 * 
 */
@Entity
@Table(name="opportunity_events")
@NamedQuery(name="OpportunityEvent.findAll", query="SELECT o FROM OpportunityEvent o")
public class OpportunityEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(nullable=false, length=30)
	private String event;

	@Column(length=1000)
	private String note;

	@Column(length=30)
	private String status;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunity;

	//bi-directional many-to-one association to People
	@ManyToOne
	@JoinColumn(name="assigned_user", nullable=false)
	private Person assignedUser;

	public OpportunityEvent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Opportunity getOpportunity() {
		return this.opportunity;
	}
		
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
		
	public Person getAssignedUser() {
		return this.assignedUser;
	}

	public void setAssignedUser(Person assignedUser) {
		this.assignedUser = assignedUser;
	}

}