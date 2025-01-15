package com.configuration.config;

import java.beans.BeanProperty;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.configuration.service.userServicenew;
import com.configuration.service.userservice;

import jakarta.websocket.Session;

//  CSRF => cross-site request forgery

@Configuration
@EnableWebSecurity
public class configuration {
	
	
	
	@Autowired
	private userservice userservice;
	
//	@Autowired
//	private userServicenew userServicenew;
	
	@Autowired
	private jwtFilter jwtfilter;
	
	
	// this method is for taking information from database for Authenticat
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider() ;
		
		authProvider.setUserDetailsService(userservice);
		
		//this use without Bcrypt password to authenticate or login
//		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		
		return authProvider;
	}
	
	
	//for JWT 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
//		http.csrf(customizer -> customizer.disable());
//		
//		http.authorizeHttpRrequests(requests -> requests.requestMatchers("hello").permitAll());
//		
//		http.authorizeHttpRequests(requests -> requests.requestMatchers("post").permitAll());
//		
//		
//		http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
//		
//		http.formLogin(Customizer.withDefaults());
//		
//		http.httpBasic(Customizer.withDefaults());
//		
//		http.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
//		return http.build();
		
		return http
				
				.csrf(customi -> customi.disable())
				
				.authorizeHttpRequests(req -> req
						.requestMatchers("post","hello","login").permitAll()
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				
				//this is for jwt 
				.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
				
				.build();
		
	} 
	
	@Bean
	public PasswordEncoder pEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
}
