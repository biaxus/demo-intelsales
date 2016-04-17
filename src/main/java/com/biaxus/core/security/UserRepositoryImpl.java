package com.biaxus.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.User;
import com.biaxus.core.domain.repository.UserRepository;

@Service
public class UserRepositoryImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserRepositoryImpl() {

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.login(username);
		return user;
	}

}
