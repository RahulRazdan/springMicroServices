package com.spring.rest.mydemo.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
import com.spring.rest.mydemo.webservices.restfulwebservices.models.Posts;
import com.spring.rest.mydemo.webservices.restfulwebservices.models.Users;
import com.spring.rest.mydemo.webservices.restfulwebservices.models.UsersV2;
import com.spring.rest.mydemo.webservices.restfulwebservices.repository.PostsRepository;
import com.spring.rest.mydemo.webservices.restfulwebservices.repository.UserRepository;

@RestController
public class HelloWorldController {

	@Autowired
	UserDaoService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	PostsRepository postRepository;
	
	/*
	 * Internatinalization methods. 
	 */
	@GetMapping(path="/internationalization")
	public String internationalization(@RequestHeader(name="Accept-Language") Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	@GetMapping(path="/internationalizationNew")
	public String internationalizationNew() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	/*
	 * Demo methods. 
	 */
	
	@GetMapping(path="/helloWorld/path/{name}")
	public HelloWorldBean helloWorld(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World ,%s", name));
	}
	
	/*
	 * JPA methods. 
	 */
	
	@GetMapping(path="/jpa/users")
	public List<Users> findAllJPAUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Posts> findAllJPAPosts(@PathVariable int id){
		
		Optional<Users> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("ID : "+id);
		
		return user.get().getPost();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public Resource<Users> findAllJPAUsers(@PathVariable int id){
		Optional<Users> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("ID : "+id);
		
		Resource<Users> resource = new Resource<>(user.get());
		
		ControllerLinkBuilder linkto = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());
		
		resource.add(linkto.withRel("all-users"));
		
		return resource;
	}
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteJPAUser(@PathVariable int id) {
		userRepository.deleteById(id);		
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> saveJPAUser(@Valid @RequestBody Users user) {
		Users savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> saveUser(@PathVariable int id,@Valid @RequestBody Posts post) {
		
		Optional<Users> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("ID : "+id);
		
		post.setUser(user.get());
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	/*
	 * Versioning  methods. 
	 */
	
	@GetMapping(path="/users",produces="application/vnd.fico.app-V1+json")
	public List<Users> findAllUsers(){
		return userService.findAll();
	}
	
	@GetMapping(path="/users",produces="application/vnd.fico.app-V2+json")
	public List<UsersV2> findAllUsersV2(){
		return userService.findAllV2();
	}
	
	/*
	 * Basic methods. 
	 */
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
