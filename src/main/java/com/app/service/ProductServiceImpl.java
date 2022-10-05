package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.ProductException;
import com.app.model.Category;
import com.app.model.Product;
import com.app.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao pdao;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		Product addedProduct=pdao.save(product);
		
		return addedProduct;
	}
	
	
	
	
	
	@Override
	public List<Product> viewAllProducts() throws ProductException {
		List<Product> products=pdao.findAll();
		if(products.size()>0) {
			return products;
		}
		else {
			throw new ProductException("Product is not Found");
		}
	}


	@Override
	public Product updateProduct(Product product) throws ProductException {
		Optional<Product> opt =   pdao.findById(product.getProductId());
		if(opt.isPresent()) {
			return pdao.save(product);
		}
		else {
			throw new ProductException("Invalid Product Details");
		}
	}

	@Override
	public Product viewProduct(Integer id) throws ProductException {
		Optional<Product> opt =  pdao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new ProductException("Product does not exist with id :"+id);
		}
	}

	@Override
	public List<Product> viewProductByCategory(String cname) throws ProductException {
		
		List<Product> products = pdao.viewByCategoryName(cname);
		
		
		if(products.size()>0) {
			return products;
		}
		else {
			throw new ProductException("Product does not exist with Category Name :"+cname);
		}
		//return new ArrayList<Product>();
	}

	@Override
	public Product removeProduct(Integer pid) throws ProductException {
	Product	existingProduct = pdao.findById(pid).orElseThrow(()->new ProductException("Product does not exist with id :"+pid));
	
	pdao.delete(existingProduct);
	
	return existingProduct ;
	
	
	}
	

}
