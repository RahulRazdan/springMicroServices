package com.spring.rest.mydemo.webservices.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.rest.mydemo.webservices.restfulwebservices.models.Users;

@Component
public class UserDaoService {

	private static List<Users> users = new ArrayList<>();
	private static int count=3;
	
	static {
		users.add(new Users(1,"Rahul",new Date()));
		users.add(new Users(2,"Razdan",new Date()));
		users.add(new Users(3,"Vipul",new Date()));
	}
	
	public List<Users> findAll() {
		return users;
	}
	
	public Users addUser(Users user) {
		if(user.getId() == 0)
			user.setId(++count);
		
		users.add(user);
		return user;
	}
	
	public Users findOne(int id) {
		for(Users user : users) {
			if(user.getId()==id)
				return user;
		}
		return null;
	}
}
