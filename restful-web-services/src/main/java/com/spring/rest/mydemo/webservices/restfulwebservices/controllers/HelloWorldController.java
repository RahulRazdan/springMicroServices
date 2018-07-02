package com.spring.rest.mydemo.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.mydemo.webservices.restfulwebservices.dao.UserDaoService;
import com.spring.rest.mydemo.webservices.restfulwebservices.model.UserNotFoundException;
import com.spring.rest.mydemo.webservices.restfulwebservices.models.HelloWorldBean;
import com.spring.rest.mydemo.webservices.restfulwebservices.models.Users;

@RestController
public class HelloWorldController {

	@Autowired
	UserDaoService userService;
	
	@GetMapping(path="/helloWorld/path/{name}")
	public HelloWorldBean helloWorld(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World ,%s", name));
	}
	
	@GetMapping(path="/users")
	public List<Users> findAllUsers(){
		return userService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public Users findAllUsers(@PathVariable int id){
		Users user = userService.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("ID : "+id);
		
		return user;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> saveUser(@RequestBody Users user) {
		Users savedUser = userService.addUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
}
