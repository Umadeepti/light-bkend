package com.dominionconsulting.tito.opp.dto;

import java.util.ArrayList;
import java.util.List;

import com.dominionconsulting.tito.opp.model.Client;
import com.dominionconsulting.tito.opp.model.SubClient;

public class ClientDto {
	
	private Integer id;
	
	private String name;
	
	private List<SubClientDto> subClients;
	
	public ClientDto() {}

	public ClientDto(Client client) {
		id = client.getId();
		name = client.getName();
		subClients = new ArrayList<SubClientDto>();
		for (SubClient subClient : client.getSubClients()) {
			subClients.add(new SubClientDto(subClient));
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

	public List<SubClientDto> getSubClients() {
		return subClients;
	}

	public void setSubClients(List<SubClientDto> subClients) {
		this.subClients = subClients;
	}

}
