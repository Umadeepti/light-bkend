package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the person_skills database table.
 * 
 */
@Entity
@Table(name="person_skills")
@NamedQuery(name="PersonSkill.findAll", query="SELECT p FROM PersonSkill p")
public class PersonSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=30)
	private String level;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date", nullable=false)
	private Date updateDate;

	private byte years;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="approver")
	private Person people1;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person", nullable=false)
	private Person people2;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skill", nullable=false)
	private Skill skillBean;

	public PersonSkill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public byte getYears() {
		return this.years;
	}

	public void setYears(byte years) {
		this.years = years;
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

	public Skill getSkillBean() {
		return this.skillBean;
	}

	public void setSkillBean(Skill skillBean) {
		this.skillBean = skillBean;
	}

}