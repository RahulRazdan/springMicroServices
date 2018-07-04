package com.spring.rest.mydemo.webservices.restfulwebservices.models;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Contains information about all existing users")
public class Users {

	private int id;
	
	@Size(min=2,message="Name should be of at least length 2")
	@ApiModelProperty(notes="At least of length 2")
	private String name;
	
	@Past
	@ApiModelProperty(notes="DOB should be in past")
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

	protected Users() {
		
	}
	public Users(Integer id, String name, Date dob) {
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
}
