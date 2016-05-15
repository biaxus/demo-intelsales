package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.User;
import com.biaxus.core.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findOne(String username){
		return userRepository.findOne(username);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public List<User> findAllSellers(){
		return userRepository.findByRoleListIdEquals("SELLER");
	}
	
	public void save(User user){
		userRepository.save(user);
	}
	
	public void delete(String username){
		userRepository.delete(username);
	}
}
