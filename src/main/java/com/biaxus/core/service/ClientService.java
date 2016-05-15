package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.Client;
import com.biaxus.core.domain.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> findAll() {
		return (List<Client>) clientRepository.findAll();
	}

	public Client save(Client enterprise) {
		return clientRepository.save(enterprise);
	}

	public void delete(Long key) {
		clientRepository.delete(key);
	}

	public Client findOne(Long key) {
		return clientRepository.findOne(key);
	}
	
}
