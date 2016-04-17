package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaxus.core.domain.EnterpriseRoleAuthority;
import com.biaxus.core.domain.EnterpriseRoleAuthorityId;

public interface RoleEnterpriseAuthorityRepository extends JpaRepository<EnterpriseRoleAuthority, EnterpriseRoleAuthorityId> {

}
