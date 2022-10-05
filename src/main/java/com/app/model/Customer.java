package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@Size(min = 3, max = 15, message = "First Name should be min 3 and max 15 Characters!")
	private String firstName;
	
	@Size(min = 1, max = 15, message = "Last Name should be min 1 and max 15 Characters!")
	private String lastName;
	
	@Size(min = 10, max = 10, message = "Mobile Number should contain 10 digit only!")
	private String mobileNumber;
	
	//@Pattern(regexp = "^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[&!@#%$]){8,16}$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character from[&!@#%^$] and 1 digit")
	@Size(min = 8, max = 16, message = "Password must contain min 8 and max 16 digits!")
	private String password;
	
	@Email(message = "Please enter a valid email...")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "c_address", joinColumns = @JoinColumn(name="customer_id", referencedColumnName = "customerId"))
	private Address address;

	public Customer(
			@Size(min = 3, max = 15, message = "First Name should be min 3 and max 15 Characters!") String firstName,
			@Size(min = 1, max = 15, message = "Last Name should be min 1 and max 15 Characters!") String lastName,
			@Min(value = 10, message = "Mobile Number should contain 10 digit only!") @Max(value = 10, message = "Mobile Number should contain 10 digit only!") String mobileNumber,
			@Pattern(regexp = "^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[&!@#%$^]){8,16}$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character from[&!@#%^$] and 1 digit") @Size(min = 8, max = 16, message = "Password must contain min 8 and max 16 digits!") String password,
			@Email(message = "Please enter a valid email...") String email, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.email = email;
		this.address = address;
	}

	
	
	

	

	
}
