package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opportunity_role_skills database table.
 * 
 */
@Entity
@Table(name="opportunity_role_skills")
@NamedQuery(name="OpportunityRoleSkill.findAll", query="SELECT o FROM OpportunityRoleSkill o")
public class OpportunityRoleSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to OpportunityRole
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private OpportunityRole opportunityRole;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skill", nullable=false)
	private Skill skillBean;

	public OpportunityRoleSkill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OpportunityRole getOpportunityRole() {
		return this.opportunityRole;
	}

	public void setOpportunityRole(OpportunityRole opportunityRole) {
		this.opportunityRole = opportunityRole;
	}

	public Skill getSkillBean() {
		return this.skillBean;
	}

	public void setSkillBean(Skill skillBean) {
		this.skillBean = skillBean;
	}

}