package com.my.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.microservices.limitsservice.bean.LimitConfiguration;
import com.my.microservices.limitsservice.config.LimitsPropertyConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {

	@Autowired
	LimitsPropertyConfiguration configuration;
	
	@GetMapping("limits")
	public LimitConfiguration getLimitConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}
	
	@GetMapping("limits-hystrix")
	@HystrixCommand(fallbackMethod="testingFallBack")
	public LimitConfiguration getHystrixLimitConfiguration() {
		throw new RuntimeException();
	}
	
	public LimitConfiguration testingFallBack() {
		return new LimitConfiguration(999, 9);
	}
}
