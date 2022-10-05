package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.AddressException;
import com.app.login.LoginException;
import com.app.model.Address;
import com.app.model.Customer;
import com.app.repository.AddressDao;
import com.app.repository.CustomerDao;
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressDao adao;
	
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CurrentUserSessionService currentUserSessionService;
	
	@Override
	public Address addAddress(Address add, String key) throws AddressException, LoginException {
	        Address	address= adao.save(add);
	        
	        Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
	        
	        currentCustomer.setAddress(address);
	        
	        customerDao.save(currentCustomer);
	        
	        return address;
	}

	@Override
	public Address updateAddress(Address add) throws AddressException {
	       Optional<Address> opt =	adao.findById(add.getAddressId());
	       if(opt.isPresent()) {
	    	   return adao.save(add);
	       }
	       else {
			throw new AddressException("Address not found");
		}
	}

	@Override
	public Address removeAddress(Integer addrId) throws AddressException {
	      
		Address existingAdd  = 	adao.findById(addrId).orElseThrow(()->new AddressException("Address does not exist :"+addrId));
	      return existingAdd;
	      
	      
	}

	@Override
	public List<Address> viewAllAddress() throws AddressException {
	 List<Address> opt= adao.findAll();
		if(opt.size()>0) {
			return opt;
		}
		else {
			throw new AddressException("Address does not exist");
		}
	}

	@Override
	public Address viewAddress(Integer id) throws AddressException {
		Optional<Address> opt= adao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new AddressException("Address does not exist");
		}
	}

}
