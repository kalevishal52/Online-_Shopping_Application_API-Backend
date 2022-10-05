package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ProductDto;
import com.app.exceptions.CartException;
import com.app.exceptions.ProductException;
import com.app.login.LoginException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.repository.CartDao;
import com.app.repository.CustomerDao;
import com.app.repository.ProductDao;
import com.app.repository.ProductDtoDao;

import lombok.Data;

@Data
@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartDao cartDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductDtoDao productDtoDao;
	
	@Autowired
	ProductService productService;

	@Autowired
	CurrentUserSessionService currentUserSessionService;
	
	@Override
	public Cart addProductToCart(Integer productId, int quantity, String key) throws CartException, LoginException, ProductException {

		//cartId, Customer , List<Products> 
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer == null) {
			throw new LoginException("Please login to add Products to the cart") ;
		}
		
		Optional<Product> optProduct = productDao.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		if(currentProduct.getQuantity() < quantity) {
			throw new ProductException("Product quantity not available or Out of stock") ;
		}
		
		Cart customerCart = cartDao.findByCustomer(currentCustomer);
		
		if(customerCart == null) { // user is adding first time in the cart 
			
			customerCart = new Cart();
			
			customerCart.setCustomer(currentCustomer);
			
			List<ProductDto> list = customerCart.getProducts();
			
			ProductDto productDto = new ProductDto( currentProduct.getProductId(),
													currentProduct.getProductName(),
													currentProduct.getPrice(), 
													currentProduct.getColor(), 
													currentProduct.getDimension(),
													currentProduct.getManufacturer(), 
													quantity);
			
			currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
			
			list.add(productDto);
			
			
			cartDao.save(customerCart) ;
			productDao.save(currentProduct);
			
			return customerCart;
				
		}
		else {
			
			List<ProductDto> list = customerCart.getProducts();
			
			ProductDto productDto = new ProductDto( currentProduct.getProductId(),
													currentProduct.getProductName(),
													currentProduct.getPrice(), 
													currentProduct.getColor(), 
													currentProduct.getDimension(),
													currentProduct.getManufacturer(), 
													quantity);
			
			currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
			
			list.add(productDto);
			
			
			cartDao.save(customerCart) ;
			productDao.save(currentProduct);
			 
			return customerCart;
			 
		}
		
		
		
		
		

	}

	@Override
	public List<ProductDto> removeProductFromCart(Integer productId, String key) throws CartException, ProductException, LoginException {
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer == null) {
			throw new LoginException("Please login to remove Products from the cart") ;
		}
		
		Optional<Product> optProduct = productDao.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		Cart customerCart = cartDao.findByCustomer(currentCustomer);
		
		if(customerCart != null) {
			List<ProductDto> list = customerCart.getProducts();
			
			boolean flag = false;

			
			for(int i = 0; i < list.size(); i++) {
				
				ProductDto productdto = list.get(i) ;
				
				if(productdto.getProductId() == productId) {
					
					//productDtoDao.delete(productdto);
					
					productDtoDao.deleteById(productdto.getId());
					
					flag = true;
					
					currentProduct.setQuantity(currentProduct.getQuantity() + productdto.getQuantity());
					productDao.save(currentProduct);
					
					list.remove(i) ;
					break;
				}
			}
			
			if(!flag) {
				throw new ProductException("There is no product with id :"+productId);
			}
			
			customerCart.setProducts(list);
			cartDao.save(customerCart);
			
			return list;
		}
		else {
			throw new ProductException("The cart is empty") ;
		}
		
	}

	@Override
	public List<ProductDto> updateProductQuantity(Integer productId,Integer quantity,String key) throws CartException, LoginException, ProductException {
		
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer == null) {
			throw new LoginException("Please login to add Products to the cart") ;
		}
		
		Optional<Product> optProduct = productDao.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		if(currentProduct.getQuantity() < quantity) {
			throw new ProductException("Product Out of stock") ;
		}
		
		Cart customerCart = cartDao.findByCustomer(currentCustomer);
		
		if(customerCart != null) {
			
			List<ProductDto> list = customerCart.getProducts();
			
			boolean flag = false;
			
			for(ProductDto productdto : list) {
				
				if(productdto.getProductId() == productId) {
					
					flag = true;
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					productdto.setQuantity(productdto.getQuantity() + quantity);
					
					productDao.save(currentProduct) ;
					productDtoDao.save(productdto) ;
					
					break;
				}
				
			}
			
			if(!flag) {
				throw new ProductException("There was no product in your cart with this id: "+" "+productId) ;
			}
			
			return list;
		}
		else {
			throw new ProductException("You have no product in the cart to update the quantity");
		}
	}

	@Override
	public Cart removeAllProducts(String key) throws CartException, LoginException {

		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer == null) {
			throw new LoginException("Please login to add Products to the cart") ;
		}
		
		Cart customerCart = cartDao.findByCustomer(currentCustomer);
		
		List<ProductDto> list = customerCart.getProducts();
		System.out.println("Hi");
		if(list.size() > 0) {
			
			
			for(ProductDto productDto : list) {
				
				Optional<Product> opt = productDao.findById(productDto.getProductId()) ;
				
				Product currentProduct = opt.get();
				
				currentProduct.setQuantity(currentProduct.getQuantity() + productDto.getQuantity());
				
				productDtoDao.delete(productDto);
				
				productDao.save(currentProduct) ;
			}
			
		}
		
		customerCart.setProducts(new ArrayList<>());
		
		return cartDao.save(customerCart) ;
	
	}

	@Override
	public List<ProductDto> viewAllProducts(String key) throws CartException, LoginException {
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer == null) {
			throw new LoginException("Please login to view all products in the cart") ;
		}
		
		Cart customerCart = cartDao.findByCustomer(currentCustomer);
		
		if(customerCart == null) {
			throw new CartException("You dont have anything in your cart");
		}
		
		return customerCart.getProducts();
	}
	
}
