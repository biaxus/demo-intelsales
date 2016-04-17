package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.AuthorityId;
import com.biaxus.core.domain.AuthorityType;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
	List<Authority> findByIdTypeIn(AuthorityType... type);

	List<Authority> findByIdKeyStartingWithAndIdTypeIn(String prefix, AuthorityType... type);

}
