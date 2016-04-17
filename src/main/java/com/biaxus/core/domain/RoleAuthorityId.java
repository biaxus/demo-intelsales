package com.biaxus.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class RoleAuthorityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ROLE", nullable = false)
	private String idRole;

	@Column(name = "AUTHORITY_KEY", nullable = false)
	private String authorityKey;

	@Enumerated(EnumType.STRING)
	@Column(name = "AUTHORITY_TYPE", nullable = false)
	private AuthorityType authorityType;

	public RoleAuthorityId() {

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
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RoleAuthorityId))
			return false;
		RoleAuthorityId other = (RoleAuthorityId) obj;
		if (authorityKey == null) {
			if (other.authorityKey != null)
				return false;
		} else if (!authorityKey.equals(other.authorityKey))
			return false;
		if (authorityType != other.authorityType)
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
		builder.append("RoleAuthorityId [idRole=");
		builder.append(idRole);
		builder.append(", authorityKey=");
		builder.append(authorityKey);
		builder.append(", authorityType=");
		builder.append(authorityType);
		builder.append("]");
		return builder.toString();
	}
}
