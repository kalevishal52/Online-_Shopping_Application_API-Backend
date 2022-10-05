package com.app.exceptions;

import java.time.LocalDateTime;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> dataValidataionException(MethodArgumentNotValidException e,WebRequest req) {
		
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), "Validation Error", HttpStatus.NON_AUTHORITATIVE_INFORMATION, e.getBindingResult().getFieldError().getDefaultMessage()) ;
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException ce, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> CustomerExceptionHandler(CustomerException ce, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorDetails> AddressExceptionHandler(AddressException ae, WebRequest req){
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ae.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> CartExceptionHandler(CartException ce, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyErrorDetails> CategoryExceptionHandler(CategoryException ce, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> orderExceptionHandler(OrderException oe, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(oe.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productExceptionHandler(ProductException pe, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(pe.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException ue, WebRequest req){
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(ue.getMessage());
			err.setHttpStatus(HttpStatus.BAD_REQUEST);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
