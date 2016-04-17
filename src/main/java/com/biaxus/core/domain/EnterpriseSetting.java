package com.biaxus.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "ENTERPRISE_SETTING")
public class EnterpriseSetting {
	
	@EmbeddedId
	//@formatter:off
	@AttributeOverrides({ 
        @AttributeOverride(name = "key", column = @Column(name = "KEY", nullable = false)),
        @AttributeOverride(name = "idEnterprise", column = @Column(name = "ID_ENTERPRISE", nullable = false))
	})
	//@formatter:on
	private EnterpriseSettingId id;
	
	@JsonBackReference("EnterpriseSetting.enterprise")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENTERPRISE", nullable = false, insertable = false, updatable = false)
	private Enterprise enterprise;
	
	@Column(name = "VALUE", nullable = false)
	private String value;

	public EnterpriseSetting() {
		
	}

	public EnterpriseSettingId getId() {
		return id;
	}

	public void setId(EnterpriseSettingId id) {
		this.id = id;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnterpriseSetting [id=");
		builder.append(id);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
