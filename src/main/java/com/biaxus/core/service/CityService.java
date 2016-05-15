package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.City;
import com.biaxus.core.domain.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findAll() {
		return (List<City>) cityRepository.findAll();
	}

	public City save(City enterprise) {
		return cityRepository.save(enterprise);
	}

	public void delete(Long key) {
		cityRepository.delete(key);
	}

	public City findOne(Long key) {
		return cityRepository.findOne(key);
	}
	
}
