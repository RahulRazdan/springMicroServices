package com.my.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.my.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.my.microservices.currencyconversionservice.bean.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CurrencyExchangeServiceProxy exchangeProxy;
	
	@Autowired
	Environment env;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retrive(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables = new HashMap<>();
		
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,uriVariables );
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(response.getBody().getId(),
				from,to,
				response.getBody().getConversionMultiple(),quantity,quantity.multiply(response.getBody().getConversionMultiple()),response.getBody().getPort());
		
		return currencyConversionBean;
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean retriveUsingFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean currencyConversionBean = exchangeProxy.retrieve(from, to);
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setCalculatedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
		
		logger.info("Response of conversion {} : ",currencyConversionBean);
		
		return currencyConversionBean;
	}
}
