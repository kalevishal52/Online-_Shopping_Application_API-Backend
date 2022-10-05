package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.login.CurrentUserSession;


public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Integer>  {

public Optional<CurrentUserSession> findByCustomerId(Integer customerId) ;
	
	public Optional<CurrentUserSession> findByUuid(String uuid) ;
	
}
