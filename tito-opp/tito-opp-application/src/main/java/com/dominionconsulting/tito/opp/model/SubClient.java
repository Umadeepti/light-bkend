package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sub_clients database table.
 * 
 */
@Entity
@Table(name="sub_clients")
@NamedQuery(name="SubClient.findAll", query="SELECT s FROM SubClient s")
public class SubClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	private String description;

	@Column(nullable=false, length=60)
	private String name;

	//bi-directional many-to-one association to Opportunity
	@OneToMany(mappedBy="subClientBean")
	private List<Opportunity> opportunities;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="client")
	private Client clientBean;

	public SubClient() {
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

	public List<Opportunity> getOpportunities() {
		return this.opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public Opportunity addOpportunity(Opportunity opportunity) {
		getOpportunities().add(opportunity);
		opportunity.setSubClientBean(this);

		return opportunity;
	}

	public Opportunity removeOpportunity(Opportunity opportunity) {
		getOpportunities().remove(opportunity);
		opportunity.setSubClientBean(null);

		return opportunity;
	}

	public Client getClientBean() {
		return this.clientBean;
	}

	public void setClientBean(Client clientBean) {
		this.clientBean = clientBean;
	}

}