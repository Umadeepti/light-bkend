package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the project_role_skills database table.
 * 
 */
@Entity
@Table(name="project_role_skills")
@NamedQuery(name="ProjectRoleSkill.findAll", query="SELECT p FROM ProjectRoleSkill p")
public class ProjectRoleSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to ProjectRole
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private ProjectRole projectRole;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skill", nullable=false)
	private Skill skillBean;

	public ProjectRoleSkill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjectRole getProjectRole() {
		return this.projectRole;
	}

	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}

	public Skill getSkillBean() {
		return this.skillBean;
	}

	public void setSkillBean(Skill skillBean) {
		this.skillBean = skillBean;
	}

}