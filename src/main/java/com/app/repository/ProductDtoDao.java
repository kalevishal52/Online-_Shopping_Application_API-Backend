package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dto.ProductDto;

public interface ProductDtoDao extends JpaRepository<ProductDto, Integer> {

	
	
}
