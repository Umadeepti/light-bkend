package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the past_performances database table.
 * 
 */
@Entity
@Table(name="past_performances")
@NamedQuery(name="PastPerformance.findAll", query="SELECT p FROM PastPerformance p")
public class PastPerformance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String note;

	@Column(name="project_name", nullable=false, length=30)
	private String projectName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date", nullable=false)
	private Date updateDate;

	@Column(length=2500)
	private String writeup;

	//bi-directional many-to-one association to OpportunityPastPerformance
	@OneToMany(mappedBy="pastPerformanceBean")
	private List<OpportunityPastPerformance> opportunityPastPerformances;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account", nullable=false)
	private Account accountBean;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project")
	private Project projectBean;

	public PastPerformance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getWriteup() {
		return this.writeup;
	}

	public void setWriteup(String writeup) {
		this.writeup = writeup;
	}

	public List<OpportunityPastPerformance> getOpportunityPastPerformances() {
		return this.opportunityPastPerformances;
	}

	public void setOpportunityPastPerformances(List<OpportunityPastPerformance> opportunityPastPerformances) {
		this.opportunityPastPerformances = opportunityPastPerformances;
	}

	public OpportunityPastPerformance addOpportunityPastPerformance(OpportunityPastPerformance opportunityPastPerformance) {
		getOpportunityPastPerformances().add(opportunityPastPerformance);
		opportunityPastPerformance.setPastPerformanceBean(this);

		return opportunityPastPerformance;
	}

	public OpportunityPastPerformance removeOpportunityPastPerformance(OpportunityPastPerformance opportunityPastPerformance) {
		getOpportunityPastPerformances().remove(opportunityPastPerformance);
		opportunityPastPerformance.setPastPerformanceBean(null);

		return opportunityPastPerformance;
	}

	public Account getAccountBean() {
		return this.accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

}