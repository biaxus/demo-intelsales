package com.biaxus.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Service;

import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.AuthorityType;
import com.biaxus.core.domain.repository.AuthorityRepository;

@Service
public class AuthorityService {

	private static final String PUBLIC_PREFIX = "ALL_";
	@Autowired
	private AuthorityRepository authorityRepository;

	public List<Authority> findByIdTypeIn(AuthorityType... type) {
		return authorityRepository.findByIdTypeIn(type);
	}

	public List<Authority> findPublicViews() {
		return authorityRepository.findByIdKeyStartingWithAndIdTypeIn(PUBLIC_PREFIX, AuthorityType.VIEW);
	}
}
