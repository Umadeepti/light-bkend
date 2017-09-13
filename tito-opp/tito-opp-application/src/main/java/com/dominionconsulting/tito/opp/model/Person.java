package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the people database table.
 * 
 */
@Entity
@Table(name="people")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="first_name", nullable=false, length=20)
	private String firstName;

	@Column(name="last_name", nullable=false, length=20)
	private String lastName;

	@Column(length=20)
	private String status;

	@Column(name="email_address", length=50)
	private String emailAddress;

	//bi-directional many-to-one association to AccountNote
	@OneToMany(mappedBy="people")
	private List<AccountNote> accountNotes;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="people")
	private List<Account> accounts;

	//bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy="manager")
	private List<Opportunity> opportunities;
	
	//bi-directional many-to-one association to OpportunityStep
	@OneToMany(mappedBy="approver")
	private List<OpportunityStep> opportunitySteps;

	//bi-directional many-to-one association to OpportunityEvent
	@OneToMany(mappedBy="assignedUser")
	private List<OpportunityEvent> opportunityEvents;

	//bi-directional many-to-one association to OpportunityNote
	@OneToMany(mappedBy="creator")
	private List<OpportunityNote> opportunityNotes;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="manager")
	private Person manager;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="manager")
	private List<Person> peoples;

	//bi-directional many-to-one association to PersonCertification
	@OneToMany(mappedBy="people1")
	private List<PersonCertification> personCertifications1;

	//bi-directional many-to-one association to PersonCertification
	@OneToMany(mappedBy="people2")
	private List<PersonCertification> personCertifications2;

	//bi-directional many-to-one association to PersonClearance
	@OneToMany(mappedBy="people1")
	private List<PersonClearance> personClearances1;

	//bi-directional many-to-one association to PersonClearance
	@OneToMany(mappedBy="people2")
	private List<PersonClearance> personClearances2;

	//bi-directional many-to-one association to PersonOpportunityRole
	@OneToMany(mappedBy="people")
	private List<PersonOpportunityRole> personOpportunityRoles;

	//bi-directional many-to-one association to PersonProjectRole
	@OneToMany(mappedBy="people")
	private List<PersonProjectRole> personProjectRoles;

	//bi-directional many-to-one association to PersonSkill
	@OneToMany(mappedBy="people1")
	private List<PersonSkill> personSkills1;

	//bi-directional many-to-one association to PersonSkill
	@OneToMany(mappedBy="people2")
	private List<PersonSkill> personSkills2;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="people")
	private List<Project> projects;

	public Person() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<AccountNote> getAccountNotes() {
		return this.accountNotes;
	}

	public void setAccountNotes(List<AccountNote> accountNotes) {
		this.accountNotes = accountNotes;
	}

	public AccountNote addAccountNote(AccountNote accountNote) {
		getAccountNotes().add(accountNote);
		accountNote.setPeople(this);

		return accountNote;
	}

	public AccountNote removeAccountNote(AccountNote accountNote) {
		getAccountNotes().remove(accountNote);
		accountNote.setPeople(null);

		return accountNote;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setPeople(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setPeople(null);

		return account;
	}

	public List<Opportunity> getOpportunities() {
		return this.opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public Opportunity addOpportunity(Opportunity opportunity) {
		getOpportunities().add(opportunity);
		opportunity.setManager(this);

		return opportunity;
	}

	public Opportunity removeOpportunity(Opportunity opportunity) {
		getOpportunities().remove(opportunity);
		opportunity.setManager(null);

		return opportunity;
	}

	// Opportunity Step Operations
	public List<OpportunityStep> getOpportunitySteps() {
		return this.opportunitySteps;
	}

	public void setOpportunitiesSteps(List<OpportunityStep> opportunitySteps) {
		this.opportunitySteps = opportunitySteps;
	}

	public OpportunityStep addOpportunitiesSteps(OpportunityStep opportunitySteps) {
		getOpportunitySteps().add(opportunitySteps);
		opportunitySteps.setApprover(this);

		return opportunitySteps;
	}

	public OpportunityStep removeOpportunitySteps(OpportunityStep opportunitySteps) {
		getOpportunitySteps().remove(opportunitySteps);
		opportunitySteps.setApprover(null);

		return opportunitySteps;
	}

	// Opportunity Event Operations
	public List<OpportunityEvent> getOpportunityEvents() {
		return this.opportunityEvents;
	}

	public void setOpportunitiesEvents(List<OpportunityEvent> opportunityEvents) {
		this.opportunityEvents = opportunityEvents;
	}

	public OpportunityEvent addOpportunitiesEvents(OpportunityEvent opportunityEvents) {
		getOpportunityEvents().add(opportunityEvents);
		opportunityEvents.setAssignedUser(this);

		return opportunityEvents;
	}

	public OpportunityEvent removeOpportunityEvents(OpportunityEvent opportunityEvents) {
		getOpportunityEvents().remove(opportunityEvents);
		opportunityEvents.setAssignedUser(null);

		return opportunityEvents;
	}

	public List<OpportunityNote> getOpportunityNotes() {
		return this.opportunityNotes;
	}

	public void setOpportunityNotes(List<OpportunityNote> opportunityNotes) {
		this.opportunityNotes = opportunityNotes;
	}

	public OpportunityNote addOpportunityNote(OpportunityNote opportunityNote) {
		getOpportunityNotes().add(opportunityNote);
		opportunityNote.setCreator(this);

		return opportunityNote;
	}

	public OpportunityNote removeOpportunityNote(OpportunityNote opportunityNote) {
		getOpportunityNotes().remove(opportunityNote);
		opportunityNote.setCreator(null);

		return opportunityNote;
	}

	public Person getPeople() {
		return this.manager;
	}

	public void setPeople(Person people) {
		this.manager = people;
	}

	public List<Person> getPeoples() {
		return this.peoples;
	}

	public void setPeoples(List<Person> peoples) {
		this.peoples = peoples;
	}

	public Person addPeople(Person people) {
		getPeoples().add(people);
		people.setPeople(this);

		return people;
	}

	public Person removePeople(Person people) {
		getPeoples().remove(people);
		people.setPeople(null);

		return people;
	}

	public List<PersonCertification> getPersonCertifications1() {
		return this.personCertifications1;
	}

	public void setPersonCertifications1(List<PersonCertification> personCertifications1) {
		this.personCertifications1 = personCertifications1;
	}

	public PersonCertification addPersonCertifications1(PersonCertification personCertifications1) {
		getPersonCertifications1().add(personCertifications1);
		personCertifications1.setPeople1(this);

		return personCertifications1;
	}

	public PersonCertification removePersonCertifications1(PersonCertification personCertifications1) {
		getPersonCertifications1().remove(personCertifications1);
		personCertifications1.setPeople1(null);

		return personCertifications1;
	}

	public List<PersonCertification> getPersonCertifications2() {
		return this.personCertifications2;
	}

	public void setPersonCertifications2(List<PersonCertification> personCertifications2) {
		this.personCertifications2 = personCertifications2;
	}

	public PersonCertification addPersonCertifications2(PersonCertification personCertifications2) {
		getPersonCertifications2().add(personCertifications2);
		personCertifications2.setPeople2(this);

		return personCertifications2;
	}

	public PersonCertification removePersonCertifications2(PersonCertification personCertifications2) {
		getPersonCertifications2().remove(personCertifications2);
		personCertifications2.setPeople2(null);

		return personCertifications2;
	}

	public List<PersonClearance> getPersonClearances1() {
		return this.personClearances1;
	}

	public void setPersonClearances1(List<PersonClearance> personClearances1) {
		this.personClearances1 = personClearances1;
	}

	public PersonClearance addPersonClearances1(PersonClearance personClearances1) {
		getPersonClearances1().add(personClearances1);
		personClearances1.setPeople1(this);

		return personClearances1;
	}

	public PersonClearance removePersonClearances1(PersonClearance personClearances1) {
		getPersonClearances1().remove(personClearances1);
		personClearances1.setPeople1(null);

		return personClearances1;
	}

	public List<PersonClearance> getPersonClearances2() {
		return this.personClearances2;
	}

	public void setPersonClearances2(List<PersonClearance> personClearances2) {
		this.personClearances2 = personClearances2;
	}

	public PersonClearance addPersonClearances2(PersonClearance personClearances2) {
		getPersonClearances2().add(personClearances2);
		personClearances2.setPeople2(this);

		return personClearances2;
	}

	public PersonClearance removePersonClearances2(PersonClearance personClearances2) {
		getPersonClearances2().remove(personClearances2);
		personClearances2.setPeople2(null);

		return personClearances2;
	}

	public List<PersonOpportunityRole> getPersonOpportunityRoles() {
		return this.personOpportunityRoles;
	}

	public void setPersonOpportunityRoles(List<PersonOpportunityRole> personOpportunityRoles) {
		this.personOpportunityRoles = personOpportunityRoles;
	}

	public PersonOpportunityRole addPersonOpportunityRole(PersonOpportunityRole personOpportunityRole) {
		getPersonOpportunityRoles().add(personOpportunityRole);
		personOpportunityRole.setPeople(this);

		return personOpportunityRole;
	}

	public PersonOpportunityRole removePersonOpportunityRole(PersonOpportunityRole personOpportunityRole) {
		getPersonOpportunityRoles().remove(personOpportunityRole);
		personOpportunityRole.setPeople(null);

		return personOpportunityRole;
	}

	public List<PersonProjectRole> getPersonProjectRoles() {
		return this.personProjectRoles;
	}

	public void setPersonProjectRoles(List<PersonProjectRole> personProjectRoles) {
		this.personProjectRoles = personProjectRoles;
	}

	public PersonProjectRole addPersonProjectRole(PersonProjectRole personProjectRole) {
		getPersonProjectRoles().add(personProjectRole);
		personProjectRole.setPeople(this);

		return personProjectRole;
	}

	public PersonProjectRole removePersonProjectRole(PersonProjectRole personProjectRole) {
		getPersonProjectRoles().remove(personProjectRole);
		personProjectRole.setPeople(null);

		return personProjectRole;
	}

	public List<PersonSkill> getPersonSkills1() {
		return this.personSkills1;
	}

	public void setPersonSkills1(List<PersonSkill> personSkills1) {
		this.personSkills1 = personSkills1;
	}

	public PersonSkill addPersonSkills1(PersonSkill personSkills1) {
		getPersonSkills1().add(personSkills1);
		personSkills1.setPeople1(this);

		return personSkills1;
	}

	public PersonSkill removePersonSkills1(PersonSkill personSkills1) {
		getPersonSkills1().remove(personSkills1);
		personSkills1.setPeople1(null);

		return personSkills1;
	}

	public List<PersonSkill> getPersonSkills2() {
		return this.personSkills2;
	}

	public void setPersonSkills2(List<PersonSkill> personSkills2) {
		this.personSkills2 = personSkills2;
	}

	public PersonSkill addPersonSkills2(PersonSkill personSkills2) {
		getPersonSkills2().add(personSkills2);
		personSkills2.setPeople2(this);

		return personSkills2;
	}

	public PersonSkill removePersonSkills2(PersonSkill personSkills2) {
		getPersonSkills2().remove(personSkills2);
		personSkills2.setPeople2(null);

		return personSkills2;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setPeople(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setPeople(null);

		return project;
	}

}