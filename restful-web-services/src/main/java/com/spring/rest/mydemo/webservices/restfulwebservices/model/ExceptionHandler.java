package com.spring.rest.mydemo.webservices.restfulwebservices.model;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	
	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> getUserExceptions(Exception e , WebRequest request){
		ExceptionResponse response = new ExceptionResponse("User Not Found",new Date(),"Either user does not exist or id incorrect.");
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> getAllExceptions(Exception e , WebRequest request){
		ExceptionResponse response = new ExceptionResponse(e.getMessage(),new Date(),e.getStackTrace().toString());
		return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
