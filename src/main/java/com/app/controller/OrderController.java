package com.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.AddressException;
import com.app.exceptions.CartException;
import com.app.exceptions.OrderException;
import com.app.login.LoginException;
import com.app.model.Customer;
import com.app.model.Orders;
import com.app.repository.CustomerDao;
import com.app.service.OrderService;

@RestController
public class OrderController {
    
	@Autowired
    private  OrderService orderService;
	
	@Autowired
	private CustomerDao customerDao;
    
    @PostMapping("/orders")
    public ResponseEntity<Orders> addOrderHandler(@RequestBody Orders order,@RequestParam String key) throws OrderException, CartException, LoginException{
    	
    	Orders addedOrder = orderService.addOrder(order, key);
    	
    	return new ResponseEntity<Orders>(addedOrder, HttpStatus.CREATED);
    }
    
    @PutMapping("/orders")
    public ResponseEntity<Orders> updateOrderHandler(@RequestBody Orders order, @RequestParam String key) throws OrderException, LoginException{
    	
    	Orders updatedOrder = orderService.updateOrder(order, key);
    	
    	return new ResponseEntity<Orders>(updatedOrder, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping("/orders/{orderid}")
    public ResponseEntity<Orders> removeOrderHandler(@PathVariable("orderid") Integer orderId, @RequestParam String uuid) throws OrderException{
    	
    	Orders deletedOrder = orderService.removeOrder(orderId, uuid);
    	
    	return new ResponseEntity<Orders>(deletedOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/orders")
    public ResponseEntity<Orders> viewOrderHandler(@RequestParam Integer orderId) throws OrderException{
    	
    	Orders existingOrder = orderService.viewOrder(orderId);
    	
    	return new ResponseEntity<Orders>(existingOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders/date/{date}")
    public ResponseEntity<List<Orders>> getAllOrdersBydateHandler(@PathVariable("date") LocalDate date) throws OrderException{
    	
    	List<Orders> orders = orderService.viewAllOrdersByDate(date);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders/loc/{city}")
    public ResponseEntity<List<Orders>> getAllOrdersByLocationHandler(@PathVariable("city") String city) throws OrderException, AddressException{
    	
    	List<Orders> orders = orderService.viewAllOrdersByLocation(city);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders")
    public ResponseEntity<List<Orders>> getAllOrdersByUserIdHandler(@RequestParam String userId) throws OrderException{
    	System.out.println(userId);
    	Optional<Customer> opt = customerDao.findByMobileNumber(userId);
    	 
    	Customer currentCustomer = opt.get();
    	
    	
    	List<Orders> orders = orderService.viewAllOrdersByUserId(userId);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }
    
    

}
