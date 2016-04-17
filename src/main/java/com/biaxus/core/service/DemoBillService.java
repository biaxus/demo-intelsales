package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.DemoBill;
import com.biaxus.core.domain.repository.DemoBillRepository;

@Service
public class DemoBillService {

	@Autowired
	private DemoBillRepository repository;

	public List<DemoBill> findAll() {
		return (List<DemoBill>) repository.findAll();
	}


}
