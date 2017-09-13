package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the certifications database table.
 * 
 */
@Entity
@Table(name="certifications")
@NamedQuery(name="Certification.findAll", query="SELECT c FROM Certification c")
public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="certification_authority", nullable=false, length=30)
	private String certificationAuthority;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=30)
	private String name;

	@Column(name="years_valid")
	private byte yearsValid;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skill")
	private Skill skillBean;

	//bi-directional many-to-one association to Solution
	@ManyToOne
	@JoinColumn(name="solution")
	private Solution solutionBean;

	//bi-directional many-to-one association to PersonCertification
	@OneToMany(mappedBy="certificationBean")
	private List<PersonCertification> personCertifications;

	public Certification() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCertificationAuthority() {
		return this.certificationAuthority;
	}

	public void setCertificationAuthority(String certificationAuthority) {
		this.certificationAuthority = certificationAuthority;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getYearsValid() {
		return this.yearsValid;
	}

	public void setYearsValid(byte yearsValid) {
		this.yearsValid = yearsValid;
	}

	public Skill getSkillBean() {
		return this.skillBean;
	}

	public void setSkillBean(Skill skillBean) {
		this.skillBean = skillBean;
	}

	public Solution getSolutionBean() {
		return this.solutionBean;
	}

	public void setSolutionBean(Solution solutionBean) {
		this.solutionBean = solutionBean;
	}

	public List<PersonCertification> getPersonCertifications() {
		return this.personCertifications;
	}

	public void setPersonCertifications(List<PersonCertification> personCertifications) {
		this.personCertifications = personCertifications;
	}

	public PersonCertification addPersonCertification(PersonCertification personCertification) {
		getPersonCertifications().add(personCertification);
		personCertification.setCertificationBean(this);

		return personCertification;
	}

	public PersonCertification removePersonCertification(PersonCertification personCertification) {
		getPersonCertifications().remove(personCertification);
		personCertification.setCertificationBean(null);

		return personCertification;
	}

}