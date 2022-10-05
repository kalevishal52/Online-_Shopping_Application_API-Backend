package com.app.controller;

import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.CustomerException;
import com.app.model.Customer;
import com.app.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{

		Customer addedCustomer = customerService.addCustomer(customer);
		
		 return new ResponseEntity<Customer>(addedCustomer, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateCustomerHandler(@PathVariable("key") String key,@RequestBody Customer customer) throws LoginException, CustomerException, com.app.login.LoginException{
		
		Customer updatedCustomer = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/customers/{key}")
	public ResponseEntity<Customer> removeCustomerHandler(@PathVariable("key") String key,@RequestBody Customer customer) throws CustomerException, LoginException, com.app.login.LoginException{
		
		Customer deletedCustomer = customerService.removeCustomer(customer, key);
		
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Customer> getCustomerHandler(@PathVariable("customerid") Integer customerId) throws CustomerException{
		
		Customer existingCustomer = customerService.viewCustomer(customerId);
		
		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/getallcustomers/{city}")
	public ResponseEntity<List<Customer>> getAllCustomersByLocation(@PathVariable("city") String city) throws CustomerException{
		
		List<Customer> customersByLocation = customerService.viewAllCustomer(city);
		
		return new ResponseEntity<List<Customer>>(customersByLocation, HttpStatus.OK);
		
	}

	
}
