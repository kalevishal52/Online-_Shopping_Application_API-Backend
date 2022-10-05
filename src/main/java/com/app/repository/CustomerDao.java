package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByMobileNumber(String mobileNumber) ;
	
}
