package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Cart;
import com.app.model.Customer;

public interface CartDao extends JpaRepository<Cart, Integer> {

	public Cart findByCustomer(Customer customer);
	
}
