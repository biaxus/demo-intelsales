package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.Enterprise;
import com.biaxus.core.domain.repository.EnterpriseRepository;

@Service
public class EnterpriseService {

	@Autowired
	private EnterpriseRepository repository;

	public List<Enterprise> findAll() {
		return (List<Enterprise>) repository.findAll();
	}

	public Enterprise save(Enterprise enterprise) {
		return repository.save(enterprise);
	}

	public void delete(String key) {
		repository.delete(key);
	}

	public Enterprise findOne(String key) {
		return repository.findOne(key);
	}

	public List<Enterprise> findByDescription(String description) {
		return repository.findByDescription(description);	
	}
	
	public List<Enterprise> findWithCycle() {
		return (List<Enterprise>) repository.findWithCycle();
	}

}
