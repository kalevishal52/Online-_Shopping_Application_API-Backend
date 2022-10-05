package com.app.service;

import com.app.model.*;
import com.app.login.*;

public interface CurrentUserSessionService {

	public CurrentUserSession getCurrentUserSession(String key) throws LoginException;
	
	public Integer getCurrentUserCustomerId(String key) throws LoginException;
	
	public Customer getCustomerDetails(String key) throws LoginException;
	
}
