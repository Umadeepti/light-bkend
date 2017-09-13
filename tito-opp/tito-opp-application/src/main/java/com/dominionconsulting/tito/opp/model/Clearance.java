package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clearances database table.
 * 
 */
@Entity
@Table(name="clearances")
@NamedQuery(name="Clearance.findAll", query="SELECT c FROM Clearance c")
public class Clearance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=20)
	private String agency;

	@Column(length=1000)
	private String description;

	@Column(name="issuing_body", length=30)
	private String issuingBody;

	@Column(nullable=false, length=30)
	private String name;

	//bi-directional many-to-one association to PersonClearance
	@OneToMany(mappedBy="clearanceBean")
	private List<PersonClearance> personClearances;

	public Clearance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgency() {
		return this.agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssuingBody() {
		return this.issuingBody;
	}

	public void setIssuingBody(String issuingBody) {
		this.issuingBody = issuingBody;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PersonClearance> getPersonClearances() {
		return this.personClearances;
	}

	public void setPersonClearances(List<PersonClearance> personClearances) {
		this.personClearances = personClearances;
	}

	public PersonClearance addPersonClearance(PersonClearance personClearance) {
		getPersonClearances().add(personClearance);
		personClearance.setClearanceBean(this);

		return personClearance;
	}

	public PersonClearance removePersonClearance(PersonClearance personClearance) {
		getPersonClearances().remove(personClearance);
		personClearance.setClearanceBean(null);

		return personClearance;
	}

}