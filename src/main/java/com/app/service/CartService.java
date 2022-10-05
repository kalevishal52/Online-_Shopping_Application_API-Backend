package com.app.service;

import java.util.List;

import com.app.dto.ProductDto;
import com.app.exceptions.CartException;
import com.app.exceptions.ProductException;
import com.app.login.LoginException;
import com.app.model.Cart;

public interface CartService {

	
	public Cart addProductToCart(Integer productId, int quantity,String key) throws CartException, LoginException, ProductException ;
	
	public List<ProductDto> removeProductFromCart(Integer productId,String key) throws CartException, ProductException, LoginException  ;
	
	public List<ProductDto> updateProductQuantity(Integer productId,Integer quantity,String key) throws CartException, LoginException, ProductException ;
	
	public Cart removeAllProducts(String key) throws CartException, LoginException ;
	
	public List<ProductDto> viewAllProducts(String key)  throws CartException, LoginException;
 
	

}
