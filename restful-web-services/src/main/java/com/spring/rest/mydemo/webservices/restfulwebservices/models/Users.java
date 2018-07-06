package com.spring.rest.mydemo.webservices.restfulwebservices.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Contains information about all existing users")
@Entity
public class Users {

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=2,message="Name should be of at least length 2")
	@ApiModelProperty(notes="At least of length 2")
	private String name;
	
	@Past
	@ApiModelProperty(notes="DOB should be in past")
	private Date dob;

	@OneToMany(mappedBy="user")
	private List<Posts> post;
	
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

	public List<Posts> getPost() {
		return post;
	}

	public void setPost(List<Posts> post) {
		this.post = post;
	}

	protected Users() {
		
	}
	public Users(Integer id, String name, Date dob) {
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
}
