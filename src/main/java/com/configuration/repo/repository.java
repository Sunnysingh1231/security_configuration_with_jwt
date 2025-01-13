package com.configuration.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.configuration.model.user;

@Repository
public interface repository extends JpaRepository<user, Integer>{

	user findUserByName(String username);
	
}
