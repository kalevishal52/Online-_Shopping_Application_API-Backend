package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@Size(min = 3, max = 15, message = "Street no. should min of 3 and max of 10 Characters")
	private String streetNo;
	
	private String buildingName;
	
	@NotNull(message = "City cannot be null.")
	@NotBlank(message = "City cannot be blank.")
	@NotEmpty(message = "City cannot be empty.")
	private String city;
	
	@NotNull(message = "State cannot be null.")
	@NotBlank(message = "State cannot be blank.")
	@NotEmpty(message = "State cannot be empty.")
	private String state;
	
	@NotNull(message = "Country cannot be null.")
	@NotBlank(message = "Country cannot be blank.")
	@NotEmpty(message = "Country cannot be empty.")
	private String country;
	
	@Size(min = 6, max = 6, message = "Pincode should be of 6 digit only")
	private String pincode;

	public Address(
			@Size(min = 3, max = 15, message = "Street no. should min of 3 and max of 10 Characters") String streetNo,
			String buildingName,
			@NotNull(message = "City cannot be null.") @NotBlank(message = "City cannot be blank.") @NotEmpty(message = "City cannot be empty.") String city,
			@NotNull(message = "State cannot be null.") @NotBlank(message = "State cannot be blank.") @NotEmpty(message = "State cannot be empty.") String state,
			@NotNull(message = "Country cannot be null.") @NotBlank(message = "Country cannot be blank.") @NotEmpty(message = "Country cannot be empty.") String country,
			@Size(min = 6, max = 6, message = "Pincode should be of 6 digit only") String pincode) {
		super();
		this.streetNo = streetNo;
		this.buildingName = buildingName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	
	
	

	
	
	
	
	
}
