package com.spring.rest.mydemo.webservices.restfulwebservices.models;

import java.util.Date;

public class Users {

	private int id;
	
	private String name;
	
	private Date dob;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}

	public Users(Integer id, String name, Date dob) {
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
}
