package com.configuration.config;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.configuration.service.userservice;

import jakarta.websocket.Session;

//  CSRF => cross-site request forgery

@Configuration
@EnableWebSecurity
public class configuration {
	
	
	
	@Autowired
	private userservice userDetailsService;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider() ;
		
		authProvider.setUserDetailsService(userDetailsService);
		
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		
		return authProvider;
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer -> customizer.disable());
		
		http.authorizeHttpRequests(requests -> requests.requestMatchers("hello").permitAll());
		
		http.authorizeHttpRequests(requests -> requests.requestMatchers("post").permitAll());
		
		
		http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
		
//		http.formLogin(Customizer.withDefaults());
		
		http.httpBasic(Customizer.withDefaults());
		
		http.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder pEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		return new InMemoryUserDetailsManager();
//		
//	}
	
}
