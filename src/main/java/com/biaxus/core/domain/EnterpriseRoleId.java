package com.biaxus.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnterpriseRoleId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ROLE", nullable = false)
	private String idRole;

	@Column(name = "ID_ENTERPRISE", nullable = false)
	private String idEnterprise;

	public EnterpriseRoleId() {
		// TODO Auto-generated constructor stub
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getIdEnterprise() {
		return idEnterprise;
	}

	public void setIdEnterprise(String idEnterprise) {
		this.idEnterprise = idEnterprise;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		EnterpriseRoleId other = (EnterpriseRoleId) obj;
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
		builder.append("EnterpriseRoleId [idRole=");
		builder.append(idRole);
		builder.append(", idEnterprise=");
		builder.append(idEnterprise);
		builder.append("]");
		return builder.toString();
	}
}
