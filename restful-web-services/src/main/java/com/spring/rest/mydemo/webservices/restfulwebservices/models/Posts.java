package com.spring.rest.mydemo.webservices.restfulwebservices.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Posts {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String post;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Users user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", post=" + post + ", user=" + user + "]";
	}
	
	
}
