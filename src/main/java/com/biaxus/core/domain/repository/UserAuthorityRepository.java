package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaxus.core.domain.UserAuthority;
import com.biaxus.core.domain.UserAuthorityId;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityId> {

}
