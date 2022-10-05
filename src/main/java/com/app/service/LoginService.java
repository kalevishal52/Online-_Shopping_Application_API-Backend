package com.app.service;

import com.app.exceptions.CustomerException;
import com.app.exceptions.UserException;
import com.app.login.CurrentUserSession;
import com.app.login.LoginException;
import com.app.model.User;

public interface LoginService {

public CurrentUserSession addUser(User user) throws UserException, CustomerException ;
	
	public User removeUser(User user,String key) throws UserException ;
	
	public User validateUser(User user,String key) throws UserException, LoginException ;
	
	public String signOut(String key) throws UserException, LoginException ;
	
}
