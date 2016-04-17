package com.biaxus.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "ENTERPRISE_ROLE_AUTHORITY")
public class EnterpriseRoleAuthority {

	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
		@AttributeOverride(name = "idEnterprise", column = @Column(name = "ID_ENTERPRISE", nullable = false)),
        @AttributeOverride(name = "idRole", column = @Column(name = "ID_ROLE", nullable = false)),
        @AttributeOverride(name = "authorityKey", column = @Column(name = "AUTHORITY_KEY", nullable = false)),
        @AttributeOverride(name = "authorityType", column = @Column(name = "AUTHORITY_TYPE", nullable = false))
	})
	//@formatter:on
	private EnterpriseRoleAuthorityId id;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUTHORITY_MODE", nullable = false)
	private AuthorityMode authorityMode;

	@JsonBackReference("EnterpriseRoleAuthority.enterpriseRole")
	@ManyToOne(fetch = FetchType.LAZY)
	// @formatter:off
	@JoinColumns({ 
		@JoinColumn(name = "ID_ENTERPRISE", referencedColumnName = "ID_ENTERPRISE", nullable = false, insertable = false, updatable = false),
	    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false, insertable = false, updatable = false) 
	})
	// @formatter:on
	private EnterpriseRole enterpriseRole;

	@JsonBackReference("RoleEnterpriseAuthority.authority")
	@ManyToOne(fetch = FetchType.LAZY)
	// @formatter:off
	@JoinColumns({ 
		@JoinColumn(name = "AUTHORITY_KEY", referencedColumnName = "KEY", nullable = false, insertable = false, updatable = false),
	    @JoinColumn(name = "AUTHORITY_TYPE", referencedColumnName = "TYPE", nullable = false, insertable = false, updatable = false) 
	})
	// @formatter:on
	private Authority authority;

	public EnterpriseRoleAuthority() {

	}

	public EnterpriseRoleAuthorityId getId() {
		return id;
	}

	public void setId(EnterpriseRoleAuthorityId id) {
		this.id = id;
	}

	public AuthorityMode getAuthorityMode() {
		return authorityMode;
	}

	public void setAuthorityMode(AuthorityMode authorityMode) {
		this.authorityMode = authorityMode;
	}

	public EnterpriseRole getEnterpriseRole() {
		return enterpriseRole;
	}

	public void setEnterpriseRole(EnterpriseRole enterpriseRole) {
		this.enterpriseRole = enterpriseRole;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseRoleAuthority [id=");
		builder.append(id);
		builder.append(", authorityMode=");
		builder.append(authorityMode);
		builder.append(", enterpriseRole=");
		builder.append(enterpriseRole);
		builder.append(", authority=");
		builder.append(authority);
		builder.append("]");
		return builder.toString();
	}
}
