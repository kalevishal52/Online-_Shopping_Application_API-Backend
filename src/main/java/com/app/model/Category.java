package com.app.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	
	private Integer categoryId;
	
	@NotNull(message = "Category name can not be null.")
	@NotEmpty(message = "Category name can not be empty.")
	@NotBlank(message = "Category name can not be blank.")
	private String categoryName;
	
	
}
