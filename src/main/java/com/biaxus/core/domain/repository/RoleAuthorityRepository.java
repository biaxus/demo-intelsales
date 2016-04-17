package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaxus.core.domain.RoleAuthority;
import com.biaxus.core.domain.RoleAuthorityId;

public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, RoleAuthorityId> {

}
