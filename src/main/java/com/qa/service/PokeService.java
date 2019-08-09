package com.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.qa.entity.User;

public interface PokeService {
	
	public List<User> getAllUsers();
	
	public Optional<User> getAUser(Long id);
	
	public ResponseEntity<Object> deleteUser(Long id);
	
	public ResponseEntity<Object> createUser(User user);
	
	public String updateUser(Long id, User user);

}
