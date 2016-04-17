package com.biaxus.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biaxus.core.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

	// @formatter:off
	@Query("select u "
			+ "from User u "
			+ "left join fetch u.userAuthorityList as userAuthorityList "
			+ "left join fetch u.userSettings "
			+ "left join fetch userAuthorityList.authority "
			+ "left join fetch u.enterprise as enterprise "
			+ "left join fetch enterprise.enterpiseSettings "
			+ "left join fetch enterprise.roleList as enterpriseRole "
			+ "left join fetch enterpriseRole.enterpriseRoleAuthorityList enterpriseRoleAuthority "
			+ "left join fetch enterpriseRoleAuthority.authority "
			+ "left join fetch u.roleList as userRole "
			+ "left join fetch userRole.roleSettings "
			+ "left join fetch userRole.roleAuthorityList as roleAuthority "
			+ "left join fetch roleAuthority.authority "
			+ "where u.username = ?1")
	// @formatter:on
	User login(String username);
}
