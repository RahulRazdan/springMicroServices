package com.spring.rest.mydemo.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping(path="/internationalization")
	public String internationalization(@RequestHeader(name="Accept-Language") Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	@GetMapping(path="/internationalizationNew")
	public String internationalizationNew() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping(path="/helloWorld/path/{name}")
	public HelloWorldBean helloWorld(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World ,%s", name));
	}
	
	@GetMapping(path="/users")
	public List<Users> findAllUsers(){
		return userService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public Resource<Users> findAllUsers(@PathVariable int id){
		Users user = userService.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("ID : "+id);
		
		Resource<Users> resource = new Resource<>(user);
		
		ControllerLinkBuilder linkto = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());
		
		resource.add(linkto.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody Users user) {
		Users savedUser = userService.addUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Users user = userService.deleteById(id);
		
		if(user==null)
			throw new UserNotFoundException("ID : "+id);
		
	}
}
