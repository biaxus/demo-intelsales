package com.biaxus.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class RoleSettingId implements Serializable {

	@Enumerated(EnumType.STRING)
	@Column(name = "KEY", nullable = false)
	private Setting key;

	@Column(name = "ID_ROLE", nullable = false)
	private String idRole;

	public RoleSettingId() {
	}
	
	public Setting getKey() {
		return key;
	}

	public void setKey(Setting key) {
		this.key = key;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		RoleSettingId other = (RoleSettingId) obj;
		if (idRole == null) {
			if (other.idRole != null)
				return false;
		} else if (!idRole.equals(other.idRole))
			return false;
		if (key != other.key)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleSettingId [key=");
		builder.append(key);
		builder.append(", idRole=");
		builder.append(idRole);
		builder.append("]");
		return builder.toString();
	}

}
