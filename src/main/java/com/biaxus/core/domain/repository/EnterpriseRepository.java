package com.biaxus.core.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaxus.core.domain.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, String> {

	@Query("SELECT DISTINCT o FROM Enterprise o LEFT JOIN FETCH o.cycleList")
	List<Enterprise> findWithCycle();

	List<Enterprise> findByDescription(String description);

}
