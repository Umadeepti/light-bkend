package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the person_opportunity_roles database table.
 * 
 */
@Entity
@Table(name="person_opportunity_roles")
@NamedQuery(name="PersonOpportunityRole.findAll", query="SELECT p FROM PersonOpportunityRole p")
public class PersonOpportunityRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to OpportunityRole
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private OpportunityRole opportunityRole;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person", nullable=false)
	private Person people;

	public PersonOpportunityRole() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public OpportunityRole getOpportunityRole() {
		return this.opportunityRole;
	}

	public void setOpportunityRole(OpportunityRole opportunityRole) {
		this.opportunityRole = opportunityRole;
	}

	public Person getPeople() {
		return this.people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}

}