package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product p where p.category.categoryName = ?1")
	public List<Product> viewByCategoryName(String cname);

}
