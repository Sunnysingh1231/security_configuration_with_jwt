package com.configuration.cont;

import org.springframework.web.bind.annotation.RestController;

import com.configuration.model.user;
import com.configuration.repo.repository;
import com.configuration.service.userServicenew;
import com.configuration.service.userservice;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class controller {
	
	@Autowired
	private repository repository;
	
	@Autowired
	private userservice userservice;
	
	@Autowired
	private userServicenew userServicenew;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("hello")
	public String hii(HttpServletRequest request) {
		return "hii => "+ request.getSession().getId();
	}

	
	@GetMapping("xtoken")
	public String xtoken(HttpServletRequest request) {
		return "xtoken => "+ request.getSession().getId();
	}
	
	@PostMapping("login")
	public ResponseEntity<String> loginpage(@RequestBody user user) {
				
		
		return new ResponseEntity<String>(userServicenew.verify(user),HttpStatus.OK);

	}
	
	
	@PostMapping("post")
	public ResponseEntity<String> postMethodName(@RequestBody user user) {
				
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		repository.save(user);
		return new ResponseEntity<String>("post successful",HttpStatus.CREATED);

	}
	
}
