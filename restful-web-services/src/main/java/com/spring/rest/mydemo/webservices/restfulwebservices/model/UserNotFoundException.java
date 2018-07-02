package com.spring.rest.mydemo.webservices.restfulwebservices.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2461263150920565087L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
