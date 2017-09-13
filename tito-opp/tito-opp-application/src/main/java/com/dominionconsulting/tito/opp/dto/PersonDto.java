package com.dominionconsulting.tito.opp.dto;

import com.dominionconsulting.tito.opp.model.Person;

public class PersonDto {
	
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	public PersonDto() {}
	
	public PersonDto(Person person) {
		id = person.getId();
		firstName = person.getFirstName();
		lastName = person.getLastName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person toPerson() {
		Person person = new Person();
		person.setId(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}

}
