package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Firm;

public class FirmDto {
	
	private Integer id;
	
	private String name;
	
	public FirmDto() {}
	
	public FirmDto(Firm firm) {
		id = firm.getId();
		name = firm.getName();
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

	public Firm toFirm() {
		Firm firm = new Firm();
		firm.setId(id);
		firm.setName(name);
		return firm;
	}

}
