package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the partner_skills database table.
 * 
 */
@Entity
@Table(name="partner_skills")
@NamedQuery(name="PartnerSkill.findAll", query="SELECT p FROM PartnerSkill p")
public class PartnerSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	@JoinColumn(name="firm", nullable=false)
	private Firm firmBean;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skill", nullable=false)
	private Skill skillBean;

	public PartnerSkill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Firm getFirmBean() {
		return this.firmBean;
	}

	public void setFirmBean(Firm firmBean) {
		this.firmBean = firmBean;
	}

	public Skill getSkillBean() {
		return this.skillBean;
	}

	public void setSkillBean(Skill skillBean) {
		this.skillBean = skillBean;
	}

}