package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project_roles database table.
 * 
 */
@Entity
@Table(name="project_roles")
@NamedQuery(name="ProjectRole.findAll", query="SELECT p FROM ProjectRole p")
public class ProjectRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="labor_category", nullable=false, length=30)
	private String laborCategory;

	@Column(length=30)
	private String level;

	@Column(precision=10, scale=2)
	private BigDecimal rate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	private byte years;

	//bi-directional many-to-one association to PersonProjectRole
	@OneToMany(mappedBy="projectRole")
	private List<PersonProjectRole> personProjectRoles;

	//bi-directional many-to-one association to ProjectRoleSkill
	@OneToMany(mappedBy="projectRole")
	private List<ProjectRoleSkill> projectRoleSkills;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project", nullable=false)
	private Project projectBean;

	public ProjectRole() {
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

	public String getLaborCategory() {
		return this.laborCategory;
	}

	public void setLaborCategory(String laborCategory) {
		this.laborCategory = laborCategory;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public byte getYears() {
		return this.years;
	}

	public void setYears(byte years) {
		this.years = years;
	}

	public List<PersonProjectRole> getPersonProjectRoles() {
		return this.personProjectRoles;
	}

	public void setPersonProjectRoles(List<PersonProjectRole> personProjectRoles) {
		this.personProjectRoles = personProjectRoles;
	}

	public PersonProjectRole addPersonProjectRole(PersonProjectRole personProjectRole) {
		getPersonProjectRoles().add(personProjectRole);
		personProjectRole.setProjectRole(this);

		return personProjectRole;
	}

	public PersonProjectRole removePersonProjectRole(PersonProjectRole personProjectRole) {
		getPersonProjectRoles().remove(personProjectRole);
		personProjectRole.setProjectRole(null);

		return personProjectRole;
	}

	public List<ProjectRoleSkill> getProjectRoleSkills() {
		return this.projectRoleSkills;
	}

	public void setProjectRoleSkills(List<ProjectRoleSkill> projectRoleSkills) {
		this.projectRoleSkills = projectRoleSkills;
	}

	public ProjectRoleSkill addProjectRoleSkill(ProjectRoleSkill projectRoleSkill) {
		getProjectRoleSkills().add(projectRoleSkill);
		projectRoleSkill.setProjectRole(this);

		return projectRoleSkill;
	}

	public ProjectRoleSkill removeProjectRoleSkill(ProjectRoleSkill projectRoleSkill) {
		getProjectRoleSkills().remove(projectRoleSkill);
		projectRoleSkill.setProjectRole(null);

		return projectRoleSkill;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

}