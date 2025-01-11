package com.configuration.cont;

import org.springframework.web.bind.annotation.RestController;

import com.configuration.model.user;
import com.configuration.repo.repository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class controller {
	
	private repository repository;

	@GetMapping("hello")
	public String hii(HttpServletRequest request) {
		return "hii => "+ request.getSession().getId();
	}
	
	
	@PostMapping("post")
	public ResponseEntity<String> postMethodName(@RequestBody user user) {
		
		return new ResponseEntity<String>("delete",HttpStatus.CREATED);
		
	}
	
}
