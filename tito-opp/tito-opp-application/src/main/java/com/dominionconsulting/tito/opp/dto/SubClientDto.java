package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.SubClient;

public class SubClientDto {
	
	private Integer id;
	
	private String name;
	
	public SubClientDto() {}

	public SubClientDto(SubClient subClient) {
		id = subClient.getId();
		name = subClient.getName();
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

	public SubClient toSubClient() {
		SubClient subClient = new SubClient();
		subClient.setId(id);
		subClient.setName(name);
		return subClient;
	}

}
