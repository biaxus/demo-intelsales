package com.biaxus.core.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Embeddable
public class EnterpriseRoleAuthorityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ENTERPRISE", nullable = false)
	private String idEnterprise;

	@Column(name = "ID_ROLE", nullable = false)
	private String idRole;

	@Column(name = "AUTHORITY_KEY", nullable = false)
	private String authorityKey;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUTHORITY_TYPE", nullable = false)
	private AuthorityType authorityType;

	public EnterpriseRoleAuthorityId() {

	}

	public String getIdEnterprise() {
		return idEnterprise;
	}

	public void setIdEnterprise(String idEnterprise) {
		this.idEnterprise = idEnterprise;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getAuthorityKey() {
		return authorityKey;
	}

	public void setAuthorityKey(String authorityKey) {
		this.authorityKey = authorityKey;
	}

	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityKey == null) ? 0 : authorityKey.hashCode());
		result = prime * result + ((authorityType == null) ? 0 : authorityType.hashCode());
		result = prime * result + ((idEnterprise == null) ? 0 : idEnterprise.hashCode());
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnterpriseRoleAuthorityId other = (EnterpriseRoleAuthorityId) obj;
		if (authorityKey == null) {
			if (other.authorityKey != null)
				return false;
		} else if (!authorityKey.equals(other.authorityKey))
			return false;
		if (authorityType != other.authorityType)
			return false;
		if (idEnterprise == null) {
			if (other.idEnterprise != null)
				return false;
		} else if (!idEnterprise.equals(other.idEnterprise))
			return false;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseRoleAuthorityId [idEnterprise=");
		builder.append(idEnterprise);
		builder.append(", idRole=");
		builder.append(idRole);
		builder.append(", authorityKey=");
		builder.append(authorityKey);
		builder.append(", authorityType=");
		builder.append(authorityType);
		builder.append("]");
		return builder.toString();
	}

}
