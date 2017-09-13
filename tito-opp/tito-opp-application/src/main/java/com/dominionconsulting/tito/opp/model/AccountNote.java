package com.dominionconsulting.tito.opp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the account_notes database table.
 * 
 */
@Entity
@Table(name="account_notes")
@NamedQuery(name="AccountNote.findAll", query="SELECT a FROM AccountNote a")
public class AccountNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", nullable=false)
	private Date createDate;

	@Column(nullable=false, length=1000)
	private String note;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="account", nullable=false)
	private Account accountBean;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="creator", nullable=false)
	private Person people;

	public AccountNote() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Account getAccountBean() {
		return this.accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public Person getPeople() {
		return this.people;
	}

	public void setPeople(Person people) {
		this.people = people;
	}

}