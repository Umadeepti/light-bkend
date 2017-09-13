package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skills database table.
 * 
 */
@Entity
@Table(name="skills")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
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
	@OneToMany(mappedBy="skillBean")
	private List<Certification> certifications;

	//bi-directional many-to-one association to OpportunityRoleSkill
	@OneToMany(mappedBy="skillBean")
	private List<OpportunityRoleSkill> opportunityRoleSkills;

	//bi-directional many-to-one association to PartnerSkill
	@OneToMany(mappedBy="skillBean")
	private List<PartnerSkill> partnerSkills;

	//bi-directional many-to-one association to PersonSkill
	@OneToMany(mappedBy="skillBean")
	private List<PersonSkill> personSkills;

	//bi-directional many-to-one association to ProjectRoleSkill
	@OneToMany(mappedBy="skillBean")
	private List<ProjectRoleSkill> projectRoleSkills;

	//bi-directional many-to-one association to Solution
	@ManyToOne
	@JoinColumn(name="solution", nullable=false)
	private Solution solutionBean;

	public Skill() {
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
		certification.setSkillBean(this);

		return certification;
	}

	public Certification removeCertification(Certification certification) {
		getCertifications().remove(certification);
		certification.setSkillBean(null);

		return certification;
	}

	public List<OpportunityRoleSkill> getOpportunityRoleSkills() {
		return this.opportunityRoleSkills;
	}

	public void setOpportunityRoleSkills(List<OpportunityRoleSkill> opportunityRoleSkills) {
		this.opportunityRoleSkills = opportunityRoleSkills;
	}

	public OpportunityRoleSkill addOpportunityRoleSkill(OpportunityRoleSkill opportunityRoleSkill) {
		getOpportunityRoleSkills().add(opportunityRoleSkill);
		opportunityRoleSkill.setSkillBean(this);

		return opportunityRoleSkill;
	}

	public OpportunityRoleSkill removeOpportunityRoleSkill(OpportunityRoleSkill opportunityRoleSkill) {
		getOpportunityRoleSkills().remove(opportunityRoleSkill);
		opportunityRoleSkill.setSkillBean(null);

		return opportunityRoleSkill;
	}

	public List<PartnerSkill> getPartnerSkills() {
		return this.partnerSkills;
	}

	public void setPartnerSkills(List<PartnerSkill> partnerSkills) {
		this.partnerSkills = partnerSkills;
	}

	public PartnerSkill addPartnerSkill(PartnerSkill partnerSkill) {
		getPartnerSkills().add(partnerSkill);
		partnerSkill.setSkillBean(this);

		return partnerSkill;
	}

	public PartnerSkill removePartnerSkill(PartnerSkill partnerSkill) {
		getPartnerSkills().remove(partnerSkill);
		partnerSkill.setSkillBean(null);

		return partnerSkill;
	}

	public List<PersonSkill> getPersonSkills() {
		return this.personSkills;
	}

	public void setPersonSkills(List<PersonSkill> personSkills) {
		this.personSkills = personSkills;
	}

	public PersonSkill addPersonSkill(PersonSkill personSkill) {
		getPersonSkills().add(personSkill);
		personSkill.setSkillBean(this);

		return personSkill;
	}

	public PersonSkill removePersonSkill(PersonSkill personSkill) {
		getPersonSkills().remove(personSkill);
		personSkill.setSkillBean(null);

		return personSkill;
	}

	public List<ProjectRoleSkill> getProjectRoleSkills() {
		return this.projectRoleSkills;
	}

	public void setProjectRoleSkills(List<ProjectRoleSkill> projectRoleSkills) {
		this.projectRoleSkills = projectRoleSkills;
	}

	public ProjectRoleSkill addProjectRoleSkill(ProjectRoleSkill projectRoleSkill) {
		getProjectRoleSkills().add(projectRoleSkill);
		projectRoleSkill.setSkillBean(this);

		return projectRoleSkill;
	}

	public ProjectRoleSkill removeProjectRoleSkill(ProjectRoleSkill projectRoleSkill) {
		getProjectRoleSkills().remove(projectRoleSkill);
		projectRoleSkill.setSkillBean(null);

		return projectRoleSkill;
	}

	public Solution getSolutionBean() {
		return this.solutionBean;
	}

	public void setSolutionBean(Solution solutionBean) {
		this.solutionBean = solutionBean;
	}

}