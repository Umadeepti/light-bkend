package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the contracts database table.
 * 
 */
@Entity
@Table(name="contracts")
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="award_amount")
	private int awardAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="award_date")
	private Date awardDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="close_date")
	private Date closeDate;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=120)
	private String name;

	private byte prime;

	@Column(length=30)
	private String type;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account", nullable=false)
	private Account accountBean;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="contractBean")
	private List<Project> projects;

	public Contract() {
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

	public Date getAwardDate() {
		return this.awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
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

	public byte getPrime() {
		return this.prime;
	}

	public void setPrime(byte prime) {
		this.prime = prime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Account getAccountBean() {
		return this.accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setContractBean(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setContractBean(null);

		return project;
	}

}