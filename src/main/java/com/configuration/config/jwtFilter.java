package com.configuration.config;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
//import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.configuration.service.JWTservice;
import com.configuration.service.userservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTservice jwTservice;
	
	@Autowired
	ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {	
		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		
		String username = null;
		
		if(authHeader != null  && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwTservice.extrateUsername(token);
		}
		
		
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = context.getBean(userservice.class).loadUserByUsername(username);
			
			if(jwTservice.validateToken(token,userDetails)) {
				
				UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				token2.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token2);
			}
		}
		
		filterChain.doFilter(request, response);
	}

	
}
