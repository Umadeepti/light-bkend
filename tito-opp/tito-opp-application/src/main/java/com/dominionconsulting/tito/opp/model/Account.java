package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=60)
	private String name;

	//bi-directional many-to-one association to AccountNote
	@OneToMany(mappedBy="accountBean")
	private List<AccountNote> accountNotes;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="owner")
	private Person people;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="accountBean")
	private List<Client> clients;

	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="accountBean")
	private List<Contract> contracts;

	//bi-directional many-to-one association to PastPerformance
	@OneToMany(mappedBy="accountBean")
	private List<PastPerformance> pastPerformances;

	public Account() {
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

	public List<AccountNote> getAccountNotes() {
		return this.accountNotes;
	}

	public void setAccountNotes(List<AccountNote> accountNotes) {
		this.accountNotes = accountNotes;
	}

	public AccountNote addAccountNote(AccountNote accountNote) {
		getAccountNotes().add(accountNote);
		accountNote.setAccountBean(this);

		return accountNote;
	}

	public AccountNote removeAccountNote(AccountNote accountNote) {
		getAccountNotes().remove(accountNote);
		accountNote.setAccountBean(null);

		return accountNote;
	}

	public Person getPeople() {
		return this.people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setAccountBean(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setAccountBean(null);

		return client;
	}

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Contract addContract(Contract contract) {
		getContracts().add(contract);
		contract.setAccountBean(this);

		return contract;
	}

	public Contract removeContract(Contract contract) {
		getContracts().remove(contract);
		contract.setAccountBean(null);

		return contract;
	}

	public List<PastPerformance> getPastPerformances() {
		return this.pastPerformances;
	}

	public void setPastPerformances(List<PastPerformance> pastPerformances) {
		this.pastPerformances = pastPerformances;
	}

	public PastPerformance addPastPerformance(PastPerformance pastPerformance) {
		getPastPerformances().add(pastPerformance);
		pastPerformance.setAccountBean(this);

		return pastPerformance;
	}

	public PastPerformance removePastPerformance(PastPerformance pastPerformance) {
		getPastPerformances().remove(pastPerformance);
		pastPerformance.setAccountBean(null);

		return pastPerformance;
	}

}