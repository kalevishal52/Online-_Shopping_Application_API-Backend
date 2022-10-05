package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.login.CurrentUserSession;
import com.app.login.LoginException;
import com.app.model.Customer;
import com.app.repository.CurrentUserSessionDao;
import com.app.repository.CustomerDao;

@Service
public class CurrentUserrSessionServiceImpl implements CurrentUserSessionService {

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public CurrentUserSession getCurrentUserSession(String key) throws LoginException {
		
		Optional<CurrentUserSession> currentUser = currentUserSessionDao.findByUuid(key);
		
		if(!currentUser.isPresent()) {
			throw new  LoginException("User has not logged in");
		}
		
		return currentUser.get();
	}

	@Override
	public Integer getCurrentUserCustomerId(String key) throws LoginException {
		Optional<CurrentUserSession> currentUser = currentUserSessionDao.findByUuid(key);
		
		if(!currentUser.isPresent()) {
			throw new  LoginException("User has not logged in");
		}
		
		return currentUser.get().getCustomerId();
	}

	@Override
	public Customer getCustomerDetails(String key) throws LoginException {
		Optional<CurrentUserSession> currentUser = currentUserSessionDao.findByUuid(key);
		
		if(!currentUser.isPresent()) {
			throw new  LoginException("User has not logged in");
		}
		
		Integer customerId = currentUser.get().getCustomerId();
		
		return customerDao.findById(customerId).get();
	}

}
