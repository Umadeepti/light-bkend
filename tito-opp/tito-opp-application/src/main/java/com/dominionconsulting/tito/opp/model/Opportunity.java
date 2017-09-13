package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the opportunities database table.
 * 
 */
@Entity
@Table(name="opportunities")
@NamedQuery(name="Opportunity.findAll", query="SELECT o FROM Opportunity o")
public class Opportunity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="aligned_to_solutions")
	private Boolean alignedToSolutions;

	@Column(name="award_type", length=30)
	private String awardType;

	@Column(name="bp_budget_required")
	private Boolean bpBudgetRequired;

	@Column(name="bp_budget_amount")
	private Integer bpBudgetAmount;

	@Column(name="contract_annual_value")
	private Integer contractAnnualValue;

	@Column(name="contract_even_distribution")
	private Boolean contractEvenDistribution;

	@Column(name="contract_type", length=30)
	private String contractType;

	@Column(name="contract_value_expected")
	private Integer contractValueExpected;

	@Column(name="contract_value_total")
	private Integer contractValueTotal;

	@Column(name="credible_prime")
	private Boolean crediblePrime;

	@Column(length=1000)
	private String description;

	@Column(name="evaluation_criteria", length=1000)
	private String evaluationCriteria;

	@Column(name="fbo_link", length=200)
	private String fboLink;

	@Column(name="gov_win_id", length=30)
	private String govWinId;

	@Column(name="gov_win_link", length=200)
	private String govWinLink;

	@Column(length=1000)
	private String investment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_time")
	private Date lastUpdatedTime;

	@Column(nullable=false, length=120)
	private String name;

	@Column(name="price_competitive")
	private Boolean priceCompetitive;

	@Column(name="price_to_win")
	private Boolean priceToWin;

	@Column(name="pricing_strategy", length=1000)
	private String pricingStrategy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="project_end_date")
	private Date projectEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="project_start_date")
	private Date projectStartDate;

	@Column(length=30)
	private String stage;

	@Column(length=30)
	private String status;

	@Column(name="supporting_past_performance")
	private Boolean supportingPastPerformance;

	@Column(name="team_assembled")
	private Boolean teamAssembled;

	@Column(name="win_probability")
	private Short winProbability;

	@Column(name="win_themes", length=1000)
	private String winThemes;

	//bi-directional many-to-one association to Firm
	@ManyToOne
	@JoinColumn(name="priming_partner")
	private Firm primingPartner;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="manager")
	private Person manager;

	//bi-directional many-to-one association to SubClient
	@ManyToOne
	@JoinColumn(name="sub_client", nullable=false)
	private SubClient subClientBean;

	//bi-directional many-to-one association to OpportunityCompetitor
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityCompetitor> opportunityCompetitors;

	//bi-directional many-to-one association to OpportunityEvent
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityEvent> opportunityEvents;

	//bi-directional many-to-one association to OpportunityIncumbent
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityIncumbent> opportunityIncumbents;

	//bi-directional many-to-one association to OpportunityNote
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityNote> opportunityNotes;

	//bi-directional many-to-one association to OpportunityPartner
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityPartner> opportunityPartners;

	//bi-directional many-to-one association to OpportunityPastPerformance
	@OneToMany(mappedBy="opportunityBean")
	private List<OpportunityPastPerformance> opportunityPastPerformances;

	//bi-directional many-to-one association to OpportunityRole
	@OneToMany(mappedBy="opportunityBean")
	private List<OpportunityRole> opportunityRoles;

	//bi-directional many-to-one association to OpportunitySolution
	@OneToMany(mappedBy = "opportunity")
	private List<OpportunitySolution> opportunitySolutions;

	//bi-directional many-to-one association to OpportunityStep
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityStep> opportunitySteps;

	//bi-directional many-to-one association to OpportunityTimeline
	@OneToMany(mappedBy="opportunity")
	private List<OpportunityTimeline> opportunityTimelines;

	public Opportunity() {
	}
	
	public Opportunity(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAlignedToSolutions() {
		return this.alignedToSolutions;
	}

	public void setAlignedToSolutions(Boolean alignedToSolutions) {
		this.alignedToSolutions = alignedToSolutions;
	}

	public String getAwardType() {
		return this.awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public Boolean getBpBudgetRequired() {
		return this.bpBudgetRequired;
	}

	public void setBpBudgetRequired(Boolean bpBudgetRequired) {
		this.bpBudgetRequired = bpBudgetRequired;
	}

	public Integer getBpBudgetAmount() {
		return this.bpBudgetAmount;
	}

	public void setBpBudgetAmount(Integer bpBudgetAmount) {
		this.bpBudgetAmount = bpBudgetAmount;
	}

	public Integer getContractAnnualValue() {
		return this.contractAnnualValue;
	}

	public void setContractAnnualValue(Integer contractAnnualValue) {
		this.contractAnnualValue = contractAnnualValue;
	}

	public Boolean getContractEvenDistribution() {
		return this.contractEvenDistribution;
	}

	public void setContractEvenDistribution(Boolean contractEvenDistribution) {
		this.contractEvenDistribution = contractEvenDistribution;
	}

	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Integer getContractValueExpected() {
		return this.contractValueExpected;
	}

	public void setContractValueExpected(Integer contractValueExpected) {
		this.contractValueExpected = contractValueExpected;
	}

	public Integer getContractValueTotal() {
		return this.contractValueTotal;
	}

	public void setContractValueTotal(Integer contractValueTotal) {
		this.contractValueTotal = contractValueTotal;
	}

	public Boolean getCrediblePrime() {
		return this.crediblePrime;
	}

	public void setCrediblePrime(Boolean crediblePrime) {
		this.crediblePrime = crediblePrime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvaluationCriteria() {
		return this.evaluationCriteria;
	}

	public void setEvaluationCriteria(String evaluationCriteria) {
		this.evaluationCriteria = evaluationCriteria;
	}

	public String getFboLink() {
		return this.fboLink;
	}

	public void setFboLink(String fboLink) {
		this.fboLink = fboLink;
	}

	public String getGovWinId() {
		return this.govWinId;
	}

	public void setGovWinId(String govWinId) {
		this.govWinId = govWinId;
	}

	public String getGovWinLink() {
		return this.govWinLink;
	}

	public void setGovWinLink(String govWinLink) {
		this.govWinLink = govWinLink;
	}

	public String getInvestment() {
		return this.investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	public Date getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPriceCompetitive() {
		return this.priceCompetitive;
	}

	public void setPriceCompetitive(Boolean priceCompetitive) {
		this.priceCompetitive = priceCompetitive;
	}

	public Boolean getPriceToWin() {
		return this.priceToWin;
	}

	public void setPriceToWin(Boolean priceToWin) {
		this.priceToWin = priceToWin;
	}

	public String getPricingStrategy() {
		return this.pricingStrategy;
	}

	public void setPricingStrategy(String pricingStrategy) {
		this.pricingStrategy = pricingStrategy;
	}

	public Date getProjectEndDate() {
		return this.projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public Date getProjectStartDate() {
		return this.projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getSupportingPastPerformance() {
		return this.supportingPastPerformance;
	}

	public void setSupportingPastPerformance(Boolean supportingPastPerformance) {
		this.supportingPastPerformance = supportingPastPerformance;
	}

	public Boolean getTeamAssembled() {
		return this.teamAssembled;
	}

	public void setTeamAssembled(Boolean teamAssembled) {
		this.teamAssembled = teamAssembled;
	}

	public Short getWinProbability() {
		return this.winProbability;
	}

	public void setWinProbability(Short winProbability) {
		this.winProbability = winProbability;
	}

	public String getWinThemes() {
		return this.winThemes;
	}

	public void setWinThemes(String winThemes) {
		this.winThemes = winThemes;
	}

	public Firm getPrimingPartner() {
		return primingPartner;
	}

	public void setPrimingPartner(Firm primingPartner) {
		this.primingPartner = primingPartner;
	}

	public Person getManager() {
		return this.manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public SubClient getSubClientBean() {
		return this.subClientBean;
	}

	public void setSubClientBean(SubClient subClientBean) {
		this.subClientBean = subClientBean;
	}

	public List<OpportunityCompetitor> getOpportunityCompetitors() {
		return this.opportunityCompetitors;
	}

	public void setOpportunityCompetitors(List<OpportunityCompetitor> opportunityCompetitors) {
		this.opportunityCompetitors = opportunityCompetitors;
	}

	public OpportunityCompetitor addOpportunityCompetitor(OpportunityCompetitor opportunityCompetitor) {
		getOpportunityCompetitors().add(opportunityCompetitor);
		opportunityCompetitor.setOpportunity(this);

		return opportunityCompetitor;
	}

	public OpportunityCompetitor removeOpportunityCompetitor(OpportunityCompetitor opportunityCompetitor) {
		getOpportunityCompetitors().remove(opportunityCompetitor);
		opportunityCompetitor.setOpportunity(null);

		return opportunityCompetitor;
	}

	public List<OpportunityEvent> getOpportunityEvents() {
		return this.opportunityEvents;
	}

	public void setOpportunityEvents(List<OpportunityEvent> opportunityEvents) {
		this.opportunityEvents = opportunityEvents;
	}

	public OpportunityEvent addOpportunityEvent(OpportunityEvent opportunityEvent) {
		getOpportunityEvents().add(opportunityEvent);
		opportunityEvent.setOpportunity(this);

		return opportunityEvent;
	}

	public OpportunityEvent removeOpportunityEvent(OpportunityEvent opportunityEvent) {
		getOpportunityEvents().remove(opportunityEvent);
		opportunityEvent.setOpportunity(null);

		return opportunityEvent;
	}

	public List<OpportunityIncumbent> getOpportunityIncumbents() {
		return this.opportunityIncumbents;
	}

	public void setOpportunityIncumbents(List<OpportunityIncumbent> opportunityIncumbents) {
		this.opportunityIncumbents = opportunityIncumbents;
	}

	public OpportunityIncumbent addOpportunityIncumbent(OpportunityIncumbent opportunityIncumbent) {
		getOpportunityIncumbents().add(opportunityIncumbent);
		opportunityIncumbent.setOpportunity(this);

		return opportunityIncumbent;
	}

	public OpportunityIncumbent removeOpportunityIncumbent(OpportunityIncumbent opportunityIncumbent) {
		getOpportunityIncumbents().remove(opportunityIncumbent);
		opportunityIncumbent.setOpportunity(null);

		return opportunityIncumbent;
	}

	public List<OpportunityNote> getOpportunityNotes() {
		return this.opportunityNotes;
	}

	public void setOpportunityNotes(List<OpportunityNote> opportunityNotes) {
		this.opportunityNotes = opportunityNotes;
	}

	public OpportunityNote addOpportunityNote(OpportunityNote opportunityNote) {
		getOpportunityNotes().add(opportunityNote);
		opportunityNote.setOpportunity(this);

		return opportunityNote;
	}

	public OpportunityNote removeOpportunityNote(OpportunityNote opportunityNote) {
		getOpportunityNotes().remove(opportunityNote);
		opportunityNote.setOpportunity(null);

		return opportunityNote;
	}

	public List<OpportunityPartner> getOpportunityPartners() {
		return this.opportunityPartners;
	}

	public void setOpportunityPartners(List<OpportunityPartner> opportunityPartners) {
		this.opportunityPartners = opportunityPartners;
	}

	public OpportunityPartner addOpportunityPartner(OpportunityPartner opportunityPartner) {
		getOpportunityPartners().add(opportunityPartner);
		opportunityPartner.setOpportunity(this);

		return opportunityPartner;
	}

	public OpportunityPartner removeOpportunityPartner(OpportunityPartner opportunityPartner) {
		getOpportunityPartners().remove(opportunityPartner);
		opportunityPartner.setOpportunity(null);

		return opportunityPartner;
	}

	public List<OpportunityPastPerformance> getOpportunityPastPerformances() {
		return this.opportunityPastPerformances;
	}

	public void setOpportunityPastPerformances(List<OpportunityPastPerformance> opportunityPastPerformances) {
		this.opportunityPastPerformances = opportunityPastPerformances;
	}

	public OpportunityPastPerformance addOpportunityPastPerformance(OpportunityPastPerformance opportunityPastPerformance) {
		getOpportunityPastPerformances().add(opportunityPastPerformance);
		opportunityPastPerformance.setOpportunityBean(this);

		return opportunityPastPerformance;
	}

	public OpportunityPastPerformance removeOpportunityPastPerformance(OpportunityPastPerformance opportunityPastPerformance) {
		getOpportunityPastPerformances().remove(opportunityPastPerformance);
		opportunityPastPerformance.setOpportunityBean(null);

		return opportunityPastPerformance;
	}

	public List<OpportunityRole> getOpportunityRoles() {
		return this.opportunityRoles;
	}

	public void setOpportunityRoles(List<OpportunityRole> opportunityRoles) {
		this.opportunityRoles = opportunityRoles;
	}

	public OpportunityRole addOpportunityRole(OpportunityRole opportunityRole) {
		getOpportunityRoles().add(opportunityRole);
		opportunityRole.setOpportunityBean(this);

		return opportunityRole;
	}

	public OpportunityRole removeOpportunityRole(OpportunityRole opportunityRole) {
		getOpportunityRoles().remove(opportunityRole);
		opportunityRole.setOpportunityBean(null);

		return opportunityRole;
	}

	public List<OpportunitySolution> getOpportunitySolutions() {
		return this.opportunitySolutions;
	}

	public void setOpportunitySolutions(List<OpportunitySolution> opportunitySolutions) {
		this.opportunitySolutions = opportunitySolutions;
	}

	public OpportunitySolution addOpportunitySolution(OpportunitySolution opportunitySolution) {
		getOpportunitySolutions().add(opportunitySolution);
		opportunitySolution.setOpportunity(this);

		return opportunitySolution;
	}

	public OpportunitySolution removeOpportunitySolution(OpportunitySolution opportunitySolution) {
		getOpportunitySolutions().remove(opportunitySolution);
		opportunitySolution.setOpportunity(null);

		return opportunitySolution;
	}

	public List<OpportunityStep> getOpportunitySteps() {
		return this.opportunitySteps;
	}

	public void setOpportunitySteps(List<OpportunityStep> opportunitySteps) {
		this.opportunitySteps = opportunitySteps;
	}

	public OpportunityStep addOpportunityStep(OpportunityStep opportunityStep) {
		getOpportunitySteps().add(opportunityStep);
		opportunityStep.setOpportunity(this);

		return opportunityStep;
	}

	public OpportunityStep removeOpportunityStep(OpportunityStep opportunityStep) {
		getOpportunitySteps().remove(opportunityStep);
		opportunityStep.setOpportunity(null);

		return opportunityStep;
	}

	public List<OpportunityTimeline> getOpportunityTimelines() {
		return this.opportunityTimelines;
	}

	public void setOpportunityTimelines(List<OpportunityTimeline> opportunityTimelines) {
		this.opportunityTimelines = opportunityTimelines;
	}

	public OpportunityTimeline addOpportunityTimeline(OpportunityTimeline opportunityTimeline) {
		getOpportunityTimelines().add(opportunityTimeline);
		opportunityTimeline.setOpportunity(this);

		return opportunityTimeline;
	}

	public OpportunityTimeline removeOpportunityTimeline(OpportunityTimeline opportunityTimeline) {
		getOpportunityTimelines().remove(opportunityTimeline);
		opportunityTimeline.setOpportunity(null);

		return opportunityTimeline;
	}

}