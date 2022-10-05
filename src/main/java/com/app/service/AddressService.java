package com.app.service;

import java.util.List;

import com.app.exceptions.AddressException;
import com.app.login.LoginException;
import com.app.model.Address;

public interface AddressService {

	public Address addAddress(Address add, String key)throws AddressException, LoginException;
	public Address updateAddress(Address add)throws AddressException;
	public Address removeAddress(Integer addressId)throws AddressException;
	public List<Address> viewAllAddress()throws AddressException;
	public Address viewAddress(Integer id)throws AddressException;
	
}
