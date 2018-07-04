package com.spring.rest.mydemo.webservices.restfulwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.rest.mydemo.webservices.restfulwebservices.models.Users;
import com.spring.rest.mydemo.webservices.restfulwebservices.models.UsersV2;

@Component
public class UserDaoService {

	private static List<Users> users = new ArrayList<>();
	private static List<UsersV2> usersV2 = new ArrayList<>();
	private static int count=3;
	
	static {
		users.add(new Users(1,"Rahul",new Date()));
		users.add(new Users(2,"Razdan",new Date()));
		users.add(new Users(3,"Vipul",new Date()));
		
		usersV2.add(new UsersV2(1,"Rahul",new Date(),"Engg"));
		usersV2.add(new UsersV2(2,"Razdan",new Date(),"Doctor"));
		usersV2.add(new UsersV2(3,"Vipul",new Date(),"CA"));
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

	public Users deleteById(int id) {

		Iterator<Users> user = users.iterator();
		
		while(user.hasNext()) {
			Users _user = user.next();
			if(_user.getId() == id) {
				user.remove();
				return _user;
			}
		}
		return null;
	}

	public List<UsersV2> findAllV2() {
		return usersV2;
	}
}
