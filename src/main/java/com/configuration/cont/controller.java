package com.configuration.cont;

import org.springframework.web.bind.annotation.RestController;

import com.configuration.model.user;
import com.configuration.repo.repository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

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
	private PasswordEncoder passwordEncoder;

	@GetMapping("hello")
	public String hii(HttpServletRequest request) {
		return "hii => "+ request.getSession().getId();
	}
	
//	@GetMapping("xtoken")
//	public CsrfToken xtoken(HttpServletRequest request) {
//		return (CsrfToken)request.getAttribute("_csrf");
//	}
	
	@GetMapping("xtoken")
	public String xtoken(HttpServletRequest request) {
		return "xtoken => "+ request.getSession().getId();
	}
	
	
	@PostMapping("post")
	public ResponseEntity<String> postMethodName(@RequestBody user user) {
		
		String encodepassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodepassword);
		
		repository.save(user);
		return new ResponseEntity<String>("post successful",HttpStatus.CREATED);

	}
	
}
