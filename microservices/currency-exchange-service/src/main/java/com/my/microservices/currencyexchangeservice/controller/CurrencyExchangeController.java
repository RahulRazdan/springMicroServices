package com.my.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.my.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.my.microservices.currencyexchangeservice.repository.ExchangeRepository;

@RestController
public class CurrencyExchangeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment env;
	
	@Autowired
	ExchangeRepository exchangeRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieve(@PathVariable String from,@PathVariable String to) {
		
		ExchangeValue exchange = exchangeRepository.findByFromAndTo(from, to); 
				
		exchange.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		logger.info("Response of exchange {} : ",exchange);
		
		return exchange;
	}
}
