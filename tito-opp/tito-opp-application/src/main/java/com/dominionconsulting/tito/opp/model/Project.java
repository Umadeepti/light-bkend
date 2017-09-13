package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(name="projects")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="award_amount")
	private int awardAmount;

	@Column(length=1000)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(length=1000)
	private String keywords;

	@Column(nullable=false, length=120)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	@Column(length=2000)
	private String summary;

	//bi-directional many-to-one association to PastPerformance
	@OneToMany(mappedBy="projectBean")
	private List<PastPerformance> pastPerformances;

	//bi-directional many-to-one association to ProjectRole
	@OneToMany(mappedBy="projectBean")
	private List<ProjectRole> projectRoles;

	//bi-directional many-to-one association to ProjectSolution
	@OneToMany(mappedBy="projectBean")
	private List<ProjectSolution> projectSolutions;

	//bi-directional many-to-one association to Contract
	@ManyToOne
	@JoinColumn(name="contract", nullable=false)
	private Contract contractBean;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="manager")
	private Person people;

	public Project() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAwardAmount() {
		return this.awardAmount;
	}

	public void setAwardAmount(int awardAmount) {
		this.awardAmount = awardAmount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<PastPerformance> getPastPerformances() {
		return this.pastPerformances;
	}

	public void setPastPerformances(List<PastPerformance> pastPerformances) {
		this.pastPerformances = pastPerformances;
	}

	public PastPerformance addPastPerformance(PastPerformance pastPerformance) {
		getPastPerformances().add(pastPerformance);
		pastPerformance.setProjectBean(this);

		return pastPerformance;
	}

	public PastPerformance removePastPerformance(PastPerformance pastPerformance) {
		getPastPerformances().remove(pastPerformance);
		pastPerformance.setProjectBean(null);

		return pastPerformance;
	}

	public List<ProjectRole> getProjectRoles() {
		return this.projectRoles;
	}

	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}

	public ProjectRole addProjectRole(ProjectRole projectRole) {
		getProjectRoles().add(projectRole);
		projectRole.setProjectBean(this);

		return projectRole;
	}

	public ProjectRole removeProjectRole(ProjectRole projectRole) {
		getProjectRoles().remove(projectRole);
		projectRole.setProjectBean(null);

		return projectRole;
	}

	public List<ProjectSolution> getProjectSolutions() {
		return this.projectSolutions;
	}

	public void setProjectSolutions(List<ProjectSolution> projectSolutions) {
		this.projectSolutions = projectSolutions;
	}

	public ProjectSolution addProjectSolution(ProjectSolution projectSolution) {
		getProjectSolutions().add(projectSolution);
		projectSolution.setProjectBean(this);

		return projectSolution;
	}

	public ProjectSolution removeProjectSolution(ProjectSolution projectSolution) {
		getProjectSolutions().remove(projectSolution);
		projectSolution.setProjectBean(null);

		return projectSolution;
	}

	public Contract getContractBean() {
		return this.contractBean;
	}

	public void setContractBean(Contract contractBean) {
		this.contractBean = contractBean;
	}

	public Person getPeople() {
		return this.people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}

}