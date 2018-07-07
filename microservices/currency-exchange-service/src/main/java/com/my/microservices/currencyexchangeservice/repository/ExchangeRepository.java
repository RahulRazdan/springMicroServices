package com.my.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.microservices.currencyexchangeservice.bean.ExchangeValue;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeValue, Long>{
	ExchangeValue findByFromAndTo(String from , String to);
}
