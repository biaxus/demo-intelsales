package com.biaxus.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class EnterpriseSettingId implements Serializable {

	@Enumerated(EnumType.STRING)
	@Column(name = "KEY", nullable = false)
	private Setting key;
	
	@Column(name = "ID_ENTERPRISE", nullable = false)
	private String idEnterprise;

	public EnterpriseSettingId() {
	
	}
	
	public Setting getKey() {
		return key;
	}

	public void setKey(Setting key) {
		this.key = key;
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
		EnterpriseSettingId other = (EnterpriseSettingId) obj;
		if (idEnterprise == null) {
			if (other.idEnterprise != null)
				return false;
		} else if (!idEnterprise.equals(other.idEnterprise))
			return false;
		if (key != other.key)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseSettingId [key=");
		builder.append(key);
		builder.append(", idEnterprise=");
		builder.append(idEnterprise);
		builder.append("]");
		return builder.toString();
	}
}