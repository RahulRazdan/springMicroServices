package com.spring.rest.mydemo.webservices.restfulwebservices.models;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Contains information about all existing users")
public class UsersV2 {

	private int id;
	
	private String occupation;
	
	@Size(min=2,message="Name should be of at least length 2")
	@ApiModelProperty(notes="At least of length 2")
	private String name;
	
	@Past
	@ApiModelProperty(notes="DOB should be in past")
	private Date dob;

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "UsersV2 [id=" + id + ", occupation=" + occupation + ", name=" + name + ", dob=" + dob + "]";
	}

	protected UsersV2() {
		
	}
	public UsersV2(Integer id, String name, Date dob,String occupation) {
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.occupation = occupation;
	}

}
