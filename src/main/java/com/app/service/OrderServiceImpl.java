package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.AddressDto;
import com.app.dto.ProductDto;
import com.app.exceptions.AddressException;
import com.app.exceptions.CartException;
import com.app.exceptions.OrderException;
import com.app.login.CurrentUserSession;
import com.app.login.LoginException;
import com.app.model.Address;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Orders;
import com.app.model.Product;
import com.app.repository.AddressDao;
import com.app.repository.CartDao;
import com.app.repository.CartRepo;
import com.app.repository.CurrentUserSessionDao;
import com.app.repository.CustomerDao;
import com.app.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao oDao;
	
	@Autowired
	CurrentUserSessionDao uSessionDao;
	
	@Autowired
	CustomerDao cDao;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	CartDao cartDao;

	@Override
	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException {
		
		 Optional<CurrentUserSession> user = uSessionDao.findByUuid(key);
		 
		 if( user.isPresent() ) {
			 
			 Integer customerId = user.get().getCustomerId();
			 
			 Optional<Customer> ourCustomer = cDao.findById(customerId);
			 
			 Address addr = ourCustomer.get().getAddress();
			 
			 
			 Orders currOrder = new Orders();
			 
			 currOrder.setOrderDate(LocalDate.now());
			 currOrder.setOrderAddress(new AddressDto(addr.getStreetNo(), addr.getBuildingName(), addr.getCity(), addr.getState(), addr.getCountry(), addr.getPincode()));
			 
			 currOrder.setCustomer(ourCustomer.get());
			 currOrder.setOrderStatus("Order confirmed");
			 
			 //List<ProductDto> list = cartRepo.getCart(customerId).getProducts();
			 
			 List<ProductDto> list = cartDao.findByCustomer(ourCustomer.get()).getProducts();
			 
			 if( list.size() < 1) {
				 throw new CartException("Add product to the cart first...");
			 }
			 
			 List<ProductDto> productList = new ArrayList<>();

			 Double total = 0.0 ;
			 
			 for(ProductDto proDto : list) {
				 
				 productList.add(proDto);
				 
				 total += (proDto.getPrice() * proDto.getQuantity()) ;
				 
			 }
			 
			 currOrder.setTotal(total);	
			 currOrder.setPList(productList); 
			 
			 Cart customerCart = cartDao.findByCustomer(ourCustomer.get()) ;
			 
			 customerCart.setProducts(new ArrayList<>());
			 
			 cartDao.save(customerCart);
			 
			 return oDao.save(currOrder);
			 
		 }
		 else {
			 throw new LoginException("Login first");
		 }
		 
		 
	}

	@Override
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException {
		
		if( uSessionDao.findByUuid(key) != null ) {
		
			Optional<Orders> opt=  oDao.findById(order.getOrderId());
			
			if(opt.isPresent()) {
				return oDao.save(order);
			}
			else {
				throw new OrderException("Order does not exist");
			}
		}
		else {
			throw new LoginException("Please, Login First...");
		}
		
	}

	@Override
	public Orders removeOrder(Integer orderId, String key) throws OrderException {
		
		Orders	existingProduct = oDao.findById(orderId).orElseThrow(()->new OrderException("Order does not exist with id :"));
		
		oDao.delete(existingProduct);
		
		return existingProduct;
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		
		Optional<Orders> opt1=  oDao.findById(orderId);
		
		if(opt1.isPresent()) {
			return opt1.get();
		}
		else {
			throw new OrderException("No order found");
		}
	}

	@Override
	public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException {
		List<Orders> orders= oDao.findByOrderDate(date);
		
		if(orders.size()>0) {
			
			return orders;
		}
		else {
			throw new OrderException("Order doesn't exist on this date.");
		}
		
	}

	@Override
	public List<Orders> viewAllOrdersByLocation(String loc) throws OrderException, AddressException {
		
		List<Orders> list= oDao.getOrderByCity(loc);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		return list;
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(String userid) throws OrderException {

		List<Orders> list = oDao.getOrdersByUserId(userid);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		
		return list;
	}

}
