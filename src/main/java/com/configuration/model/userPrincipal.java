package com.configuration.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class userPrincipal implements UserDetails{
	
	private user u2;

	
	
	
	public userPrincipal(user u2) {
		super();
		this.u2 = u2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u2.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return u2.getName();
	}

	
	
	
}
