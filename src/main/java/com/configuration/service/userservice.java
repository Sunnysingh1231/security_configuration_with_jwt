package com.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.configuration.model.user;
import com.configuration.model.userPrincipal;
import com.configuration.repo.repository;


@Service
public class userservice implements UserDetailsService{

	@Autowired
	private repository reopRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user u1 = reopRepository.findUserByName(username);
		
		if(u1 == null) {
			throw new UsernameNotFoundException("404 not found");
		}
		
		
		return new userPrincipal(u1);
	}

}
