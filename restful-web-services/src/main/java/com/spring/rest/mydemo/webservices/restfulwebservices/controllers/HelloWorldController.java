package com.spring.rest.mydemo.webservices.restfulwebservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.mydemo.webservices.restfulwebservices.dao.UserDaoService;
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
}
