package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Account;
import com.dominionconsulting.tito.opp.model.Client;
import com.dominionconsulting.tito.opp.model.Opportunity;
import com.dominionconsulting.tito.opp.model.Person;
import com.dominionconsulting.tito.opp.model.SubClient;

public class OppHomeDto {
	
	private Integer id;
	
	private String name;
	
	private Integer accountId;
	
	private String accountName;
	
	private Integer managerId;
	
	private String managerFirstName;
	
	private String managerLastName;
	
	private String stage;
	
	public OppHomeDto() {}
	
	public OppHomeDto(Opportunity opp) {
		id = opp.getId();
		name = opp.getName();
		SubClient subClient = opp.getSubClientBean();
		if (subClient != null) {
			Client client = subClient.getClientBean();
			if (client != null) {
				Account account = client.getAccountBean();
				if (account != null) {
					accountId = account.getId();
					accountName = account.getName();
				}
			}
		}
		Person manager = opp.getManager();
		if (manager != null) {
			managerId = manager.getId();
			managerFirstName = manager.getFirstName();
			managerLastName = manager.getLastName();
		}
		stage = opp.getStage();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer subClientClientAccountId) {
		this.accountId = subClientClientAccountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String subClientClientAccountName) {
		this.accountName = subClientClientAccountName;
	}

	public Integer getManagerId() {
		return managerId;
	}
	
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
