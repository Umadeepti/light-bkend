package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the solutions database table.
 * 
 */
@Entity
@Table(name="solutions")
@NamedQuery(name="Solution.findAll", query="SELECT s FROM Solution s")
public class Solution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=30)
	private String name;

	//bi-directional many-to-one association to Certification
	@OneToMany(mappedBy="solutionBean")
	private List<Certification> certifications;

	//bi-directional many-to-one association to OpportunitySolution
	@OneToMany(mappedBy="solutionBean")
	private List<OpportunitySolution> opportunitySolutions;

	//bi-directional many-to-one association to ProjectSolution
	@OneToMany(mappedBy="solutionBean")
	private List<ProjectSolution> projectSolutions;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="solutionBean")
	private List<Skill> skills;

	public Solution() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	public Certification addCertification(Certification certification) {
		getCertifications().add(certification);
		certification.setSolutionBean(this);

		return certification;
	}

	public Certification removeCertification(Certification certification) {
		getCertifications().remove(certification);
		certification.setSolutionBean(null);

		return certification;
	}

	public List<OpportunitySolution> getOpportunitySolutions() {
		return this.opportunitySolutions;
	}

	public void setOpportunitySolutions(List<OpportunitySolution> opportunitySolutions) {
		this.opportunitySolutions = opportunitySolutions;
	}

	public OpportunitySolution addOpportunitySolution(OpportunitySolution opportunitySolution) {
		getOpportunitySolutions().add(opportunitySolution);
		opportunitySolution.setSolutionBean(this);

		return opportunitySolution;
	}

	public OpportunitySolution removeOpportunitySolution(OpportunitySolution opportunitySolution) {
		getOpportunitySolutions().remove(opportunitySolution);
		opportunitySolution.setSolutionBean(null);

		return opportunitySolution;
	}

	public List<ProjectSolution> getProjectSolutions() {
		return this.projectSolutions;
	}

	public void setProjectSolutions(List<ProjectSolution> projectSolutions) {
		this.projectSolutions = projectSolutions;
	}

	public ProjectSolution addProjectSolution(ProjectSolution projectSolution) {
		getProjectSolutions().add(projectSolution);
		projectSolution.setSolutionBean(this);

		return projectSolution;
	}

	public ProjectSolution removeProjectSolution(ProjectSolution projectSolution) {
		getProjectSolutions().remove(projectSolution);
		projectSolution.setSolutionBean(null);

		return projectSolution;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setSolutionBean(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setSolutionBean(null);

		return skill;
	}

}