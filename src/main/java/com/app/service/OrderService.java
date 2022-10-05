package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.exceptions.AddressException;
import com.app.exceptions.CartException;
import com.app.exceptions.OrderException;
import com.app.login.LoginException;
import com.app.model.Orders;

public interface OrderService {

	
		public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException;
		public Orders updateOrder(Orders order, String key) throws OrderException, LoginException;
		public Orders removeOrder(Integer oriderId, String key) throws OrderException;
		public Orders viewOrder(Integer orderId) throws OrderException;
		public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException;
		public List<Orders> viewAllOrdersByLocation(String city) throws OrderException, AddressException;
		public List<Orders> viewAllOrdersByUserId(String userid) throws OrderException;
		

	
}
