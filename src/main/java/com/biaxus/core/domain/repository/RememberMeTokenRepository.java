package com.biaxus.core.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.biaxus.core.domain.RememberMeToken;

public interface RememberMeTokenRepository extends CrudRepository<RememberMeToken, String> {
	List<RememberMeToken> findByUserUsername(String username);
	
	@Query("select rt from RememberMeToken rt inner join fetch rt.user where rt.series = ?1")
	RememberMeToken findBySeriesWithUser(String series);
}
