package com.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.ott.GenerateOneTimeTokenFilter;
import org.springframework.stereotype.Service;

import com.configuration.model.user;

@Service
public class userServicenew {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTservice jwTservice;
	
	public String verify(user user) {
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		
		if(auth.isAuthenticated())
//			return "successful";
			return jwTservice.GenerateToken(user.getName());
		
		return "failed";
	}
	
}
