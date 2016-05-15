package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaxus.core.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
