package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the opportunity_roles database table.
 * 
 */
@Entity
@Table(name="opportunity_roles")
@NamedQuery(name="OpportunityRole.findAll", query="SELECT o FROM OpportunityRole o")
public class OpportunityRole implements Serializable {
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

	//bi-directional many-to-one association to OpportunityRoleSkill
	@OneToMany(mappedBy="opportunityRole")
	private List<OpportunityRoleSkill> opportunityRoleSkills;

	//bi-directional many-to-one association to Opportunity
	@ManyToOne
	@JoinColumn(name="opportunity", nullable=false)
	private Opportunity opportunityBean;

	//bi-directional many-to-one association to PersonOpportunityRole
	@OneToMany(mappedBy="opportunityRole")
	private List<PersonOpportunityRole> personOpportunityRoles;

	public OpportunityRole() {
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

	public List<OpportunityRoleSkill> getOpportunityRoleSkills() {
		return this.opportunityRoleSkills;
	}

	public void setOpportunityRoleSkills(List<OpportunityRoleSkill> opportunityRoleSkills) {
		this.opportunityRoleSkills = opportunityRoleSkills;
	}

	public OpportunityRoleSkill addOpportunityRoleSkill(OpportunityRoleSkill opportunityRoleSkill) {
		getOpportunityRoleSkills().add(opportunityRoleSkill);
		opportunityRoleSkill.setOpportunityRole(this);

		return opportunityRoleSkill;
	}

	public OpportunityRoleSkill removeOpportunityRoleSkill(OpportunityRoleSkill opportunityRoleSkill) {
		getOpportunityRoleSkills().remove(opportunityRoleSkill);
		opportunityRoleSkill.setOpportunityRole(null);

		return opportunityRoleSkill;
	}

	public Opportunity getOpportunityBean() {
		return this.opportunityBean;
	}

	public void setOpportunityBean(Opportunity opportunityBean) {
		this.opportunityBean = opportunityBean;
	}

	public List<PersonOpportunityRole> getPersonOpportunityRoles() {
		return this.personOpportunityRoles;
	}

	public void setPersonOpportunityRoles(List<PersonOpportunityRole> personOpportunityRoles) {
		this.personOpportunityRoles = personOpportunityRoles;
	}

	public PersonOpportunityRole addPersonOpportunityRole(PersonOpportunityRole personOpportunityRole) {
		getPersonOpportunityRoles().add(personOpportunityRole);
		personOpportunityRole.setOpportunityRole(this);

		return personOpportunityRole;
	}

	public PersonOpportunityRole removePersonOpportunityRole(PersonOpportunityRole personOpportunityRole) {
		getPersonOpportunityRoles().remove(personOpportunityRole);
		personOpportunityRole.setOpportunityRole(null);

		return personOpportunityRole;
	}

}