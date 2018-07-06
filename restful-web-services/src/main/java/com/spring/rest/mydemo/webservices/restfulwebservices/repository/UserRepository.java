package com.spring.rest.mydemo.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.mydemo.webservices.restfulwebservices.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
