package com.configuration.config;

import java.beans.BeanProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.websocket.Session;

//  CSRF => cross-site request forgery

@Configuration
@EnableWebSecurity
public class configuration {
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/findallusers")
//                        .requestMatchers("/addnewuser")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .formLogin(withDefaults());
//		
//		return httpSecurity.build();
//	}
	
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
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new InMemoryUserDetailsManager();
		
	}
	
}
