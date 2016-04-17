package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biaxus.core.domain.Authority;
import com.biaxus.core.domain.EnterpriseRole;
import com.biaxus.core.domain.EnterpriseRoleId;

public interface EnterpriseRoleRepository extends JpaRepository<EnterpriseRole, EnterpriseRoleId> {

}
