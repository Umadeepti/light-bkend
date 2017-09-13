package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the person_certifications database table.
 * 
 */
@Entity
@Table(name="person_certifications")
@NamedQuery(name="PersonCertification.findAll", query="SELECT p FROM PersonCertification p")
public class PersonCertification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="earn_date", nullable=false)
	private Date earnDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expire_date")
	private Date expireDate;

	//bi-directional many-to-one association to Certification
	@ManyToOne
	@JoinColumn(name="certification", nullable=false)
	private Certification certificationBean;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="approver")
	private Person people1;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person", nullable=false)
	private Person people2;

	public PersonCertification() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEarnDate() {
		return this.earnDate;
	}

	public void setEarnDate(Date earnDate) {
		this.earnDate = earnDate;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Certification getCertificationBean() {
		return this.certificationBean;
	}

	public void setCertificationBean(Certification certificationBean) {
		this.certificationBean = certificationBean;
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