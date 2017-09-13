package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Account;
import com.dominionconsulting.tito.opp.model.Client;

public class AccountDto {
	
	private Integer id;
	
	private String name;
	
	private List<ClientDto> clients;
	
	public AccountDto() {}

	public AccountDto(Account account) {
		id = account.getId();
		name = account.getName();
		clients = new ArrayList<ClientDto>();
		for (Client client : account.getClients()) {
			clients.add(new ClientDto(client));
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClientDto> getClients() {
		return clients;
	}

	public void setClients(List<ClientDto> clients) {
		this.clients = clients;
	}

}
