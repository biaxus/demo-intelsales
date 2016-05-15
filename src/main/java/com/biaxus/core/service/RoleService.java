package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.Role;
import com.biaxus.core.domain.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}

	public Role save(Role enterprise) {
		return roleRepository.save(enterprise);
	}

	public void delete(String key) {
		roleRepository.delete(key);
	}

	public Role findOne(String key) {
		return roleRepository.findOne(key);
	}
	
}
