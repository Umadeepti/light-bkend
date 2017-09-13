package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the person_clearances database table.
 * 
 */
@Entity
@Table(name="person_clearances")
@NamedQuery(name="PersonClearance.findAll", query="SELECT p FROM PersonClearance p")
public class PersonClearance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expire_date")
	private Date expireDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="issue_date", nullable=false)
	private Date issueDate;

	//bi-directional many-to-one association to Clearance
	@ManyToOne
	@JoinColumn(name="clearance", nullable=false)
	private Clearance clearanceBean;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="approver")
	private Person people1;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person", nullable=false)
	private Person people2;

	public PersonClearance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Clearance getClearanceBean() {
		return this.clearanceBean;
	}

	public void setClearanceBean(Clearance clearanceBean) {
		this.clearanceBean = clearanceBean;
	}

	public Person getPeople1() {
		return this.people1;
	}

	public void setPeople1(Person people1) {
		this.people1 = people1;
	}

	public Person getPeople2() {
		return this.people2;
	}

	public void setPeople2(Person people2) {
		this.people2 = people2;
	}

}