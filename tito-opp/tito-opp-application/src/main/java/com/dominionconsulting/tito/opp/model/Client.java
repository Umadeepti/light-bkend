package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=60)
	private String name;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account")
	private Account accountBean;

	//bi-directional many-to-one association to SubClient
	@OneToMany(mappedBy="clientBean")
	private List<SubClient> subClients;

	public Client() {
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

	public Account getAccountBean() {
		return this.accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public List<SubClient> getSubClients() {
		return this.subClients;
	}

	public void setSubClients(List<SubClient> subClients) {
		this.subClients = subClients;
	}

	public SubClient addSubClient(SubClient subClient) {
		getSubClients().add(subClient);
		subClient.setClientBean(this);

		return subClient;
	}

	public SubClient removeSubClient(SubClient subClient) {
		getSubClients().remove(subClient);
		subClient.setClientBean(null);

		return subClient;
	}

}