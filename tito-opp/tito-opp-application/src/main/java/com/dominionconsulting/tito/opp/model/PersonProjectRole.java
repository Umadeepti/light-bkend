package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the person_project_roles database table.
 * 
 */
@Entity
@Table(name="person_project_roles")
@NamedQuery(name="PersonProjectRole.findAll", query="SELECT p FROM PersonProjectRole p")
public class PersonProjectRole implements Serializable {
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

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person", nullable=false)
	private Person people;

	//bi-directional many-to-one association to ProjectRole
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private ProjectRole projectRole;

	public PersonProjectRole() {
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

	public Person getPeople() {
		return this.people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}

	public ProjectRole getProjectRole() {
		return this.projectRole;
	}

	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}

}